package com.mservicetech.campsite.service;

import com.mservicetech.campsite.exception.ServiceProcessException;
import com.mservicetech.campsite.model.AvailableDates;
import com.mservicetech.campsite.model.Client;
import com.mservicetech.campsite.model.Reservation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CampsiteServiceImpl implements CampsiteService{

    @Override
    public AvailableDates getAvailableDates(LocalDate startDate, LocalDate endDate) throws ServiceProcessException {
        AvailableDates availableDates = new AvailableDates();
        List<LocalDate> result = new ArrayList<>();
        result.add(LocalDate.now());
        availableDates.setDatelist(result);
        return availableDates;
    }

    @Override
    public Reservation createReservation(Reservation reservation) throws ServiceProcessException {
        return reservation;
    }

    @Override
    public Reservation updateReservation(String orderId, Reservation reservation) throws ServiceProcessException {
        return reservation;
    }

    @Override
    public Reservation deleteReservation(String orderId) throws ServiceProcessException {
        Reservation reservation = new Reservation();
        Client client = new Client();
        client.setEmail("George.Jordan@gmail.com");
        client.setName("George Jordan");
        reservation.setArrival(LocalDate.now());
        reservation.setDeparture(LocalDate.now());
        reservation.setClient(client);
        return reservation;
    }
}
