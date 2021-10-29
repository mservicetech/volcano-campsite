package com.mservicetech.campsite.service;


import com.mservicetech.campsite.exception.ServiceProcessException;
import com.mservicetech.campsite.model.AvailableDates;
import com.mservicetech.campsite.model.Reservation;

import java.time.LocalDate;

public interface CampsiteService {
	

	AvailableDates getAvailableDates(LocalDate startDate, LocalDate endDate) throws ServiceProcessException;
	Reservation createReservation(Reservation reservation) throws ServiceProcessException;
	Reservation updateReservation(String orderId, Reservation reservation) throws ServiceProcessException;
	Reservation deleteReservation(String orderId) throws ServiceProcessException;
}
