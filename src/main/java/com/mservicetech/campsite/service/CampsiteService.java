package com.mservicetech.campsite.service;


import com.mservicetech.campsite.model.AvailableDates;
import com.mservicetech.campsite.model.Reservation;

import java.time.LocalDate;

public interface CampsiteService {
	

	AvailableDates getAvailableDates(LocalDate startDate, LocalDate endDate);
	Reservation createReservation(Reservation reservation) ;
	Reservation updateReservation(String reservationId, Reservation reservation) ;
	Reservation deleteReservation(String reservationId) ;
}
