package com.mservicetech.campsite.repository;

import com.mservicetech.campsite.model.Client;
import com.mservicetech.campsite.model.Reservation;

import java.time.LocalDate;
import java.util.List;

/**
 * Define data repository  interface methods
 * <p>
 * Supports volcano campsite reservation information to persist to backend repository
 *
 * @author Gavin Chen
 */
public interface CampsiteRepository {

    List<LocalDate> findReserved();

    int reserve(Reservation reservation);

    int reserveDates(List<LocalDate> dateList);

    int deleteDates(List<LocalDate> dateList);

    List<LocalDate> verifyDates(List<LocalDate> dateList);

    List<Client> checkClientExisting(Client client);

    long insertClient(Client client);

    String createReservation(Reservation reservation);

    Reservation getReservation(String reservationId);

    int deleteReservation(String reservationId);

    int updateReservation(Reservation reservation);
}
