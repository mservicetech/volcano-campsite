package com.mservicetech.campsite.service;

import com.mservicetech.campsite.exception.DataNotFoundException;
import com.mservicetech.campsite.exception.DataProcessException;
import com.mservicetech.campsite.model.AvailableDates;
import com.mservicetech.campsite.model.Client;
import com.mservicetech.campsite.model.Reservation;
import com.mservicetech.campsite.repository.CampsiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CampsiteServiceImpl implements CampsiteService{

    private static final Logger logger= LoggerFactory.getLogger(CampsiteService.class);

    @Autowired
    private CampsiteRepository campsiteRepository;

    @Override
    public AvailableDates getAvailableDates(LocalDate startDate, LocalDate endDate) {
        //TODO validation for input parameters
        AvailableDates availableDates = new AvailableDates();
        List<LocalDate> result = new ArrayList<>();
        if (startDate==null) startDate = LocalDate.now();
        if (endDate==null) endDate = LocalDate.now().plusDays(30);
        long days = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay() ).toDays();
        for (int i=1; i<=days; i++) {
            result.add(startDate.plusDays(i));
        }
        List<LocalDate> reservedDates = campsiteRepository.findReserved();
        result.removeAll(reservedDates);
        availableDates.setDatelist(result);
        availableDates.setStartDate(startDate);
        availableDates.setEndDate(endDate);
        return availableDates;
    }

    @Override
    @Transactional
    public Reservation createReservation(Reservation reservation) {
        //TODO validation
        List<LocalDate> dateList = new ArrayList<>();
        long days = Duration.between(reservation.getArrival().atStartOfDay(), reservation.getDeparture().atStartOfDay() ).toDays();
        for (int i=0; i<=days; i++) {
            dateList.add(reservation.getArrival().plusDays(i));
        }
        //TODO check the dates again
        List<Client> clients = campsiteRepository.checkClientExisting(reservation.getClient());
        if (clients.size()==0 ) {
            long clientId = campsiteRepository.insertClient(reservation.getClient());
            reservation.getClient().setId(clientId);
            logger.debug("new client inserted, client id:" + clientId);
        } else {
            //TODO if client existing by name is different as database, do we need update client??
            reservation.getClient().setId(clients.get(0).getId());
        }

        try {
            int records = campsiteRepository.reserveDates(dateList);
            if (logger.isDebugEnabled()) logger.debug("Total days:" + records + " reserved for client" + reservation.getClient().getName());
            String reservationId =  campsiteRepository.createReservation(reservation);
            if (logger.isDebugEnabled()) logger.debug("New Reservation created:" + reservationId);
            reservation.setId(reservationId);
        } catch (Exception e) {
            logger.error("Error on the reservation:" + e);
            throw new DataProcessException("Cannot add reservation; Some of dates have been reserved. Please try again");
        }
        return reservation;
    }

    @Override
    @Transactional
    public Reservation updateReservation(String reservationId, Reservation reservation)  {
        Reservation oldReservation;
        try {
            oldReservation = campsiteRepository.getReservation(reservationId);
        } catch (Exception e) {
            logger.error("Error on the reservation:" + e);
            throw new DataNotFoundException("Cannot found reservation, input reservation id is not existing.");
        }
        if (oldReservation!=null) {
            List<LocalDate> dateList = new ArrayList<>();
            long days = Duration.between(oldReservation.getArrival().atStartOfDay(), oldReservation.getDeparture().atStartOfDay() ).toDays();
            for (int i=0; i<=days; i++) {
                dateList.add(reservation.getArrival().plusDays(i));
            }
            campsiteRepository.deleteDates(dateList);
            reservation.setId(reservationId);
            campsiteRepository.updateReservation(reservation);
            dateList.clear();
            days = Duration.between(reservation.getArrival().atStartOfDay(), reservation.getDeparture().atStartOfDay() ).toDays();
            for (int i=0; i<=days; i++) {
                dateList.add(reservation.getArrival().plusDays(i));
            }
            campsiteRepository.reserveDates(dateList);
        }
        return reservation;
    }

    @Override
    @Transactional
    public Reservation deleteReservation(String reservationId)  {
        Reservation reservation;
        try {
            reservation = campsiteRepository.getReservation(reservationId);
        } catch (Exception e) {
            logger.error("Error on the reservation:" + e);
            throw new DataNotFoundException("Cannot found reservation, input reservation id is not existing.");
        }
        //TODO verify client before delete reservation??

        if (reservation!=null) {
            List<LocalDate> dateList = new ArrayList<>();
            long days = Duration.between(reservation.getArrival().atStartOfDay(), reservation.getDeparture().atStartOfDay() ).toDays();
            for (int i=0; i<=days; i++) {
                dateList.add(reservation.getArrival().plusDays(i));
            }
            campsiteRepository.deleteDates(dateList);
            campsiteRepository.deleteReservation(reservationId);
        }
        return reservation;
    }
}
