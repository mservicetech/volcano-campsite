package com.mservicetech.campsite.service;


import com.mservicetech.campsite.exception.DataNotFoundException;
import com.mservicetech.campsite.exception.DataProcessException;
import com.mservicetech.campsite.model.AvailableDates;
import com.mservicetech.campsite.model.Client;
import com.mservicetech.campsite.model.Reservation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class CampsiteServiceImplTest {

    @Autowired
    private CampsiteService campsiteService;
    private static Client client;
    private static Reservation reservation;

    @BeforeAll
    public static void setUp() {
        client = new Client();
        client.setName("test");
        client.setEmail("test.admin@gmail.com");
        reservation = new Reservation();
        reservation.setClient(client);
        reservation.setArrival(LocalDate.now());
        reservation.setDeparture(LocalDate.now().plusDays(3));
    }

    @Test
    public void testTimeDuration()  {
        LocalDate today = LocalDate.now();
        LocalDate future = today.plusDays(30);
        long result = Duration.between(today.atStartOfDay(), future.atStartOfDay() ).toDays();
        assertEquals(result, 30);
    }

    @Test
    public void testGetAvailableDates()  {
        AvailableDates availableDates = campsiteService.getAvailableDates(LocalDate.now(), LocalDate.now().plusDays(5));
        assertEquals(availableDates.getDatelist().size(), 5);
    }

    @Test
    public void testCreateReservation()  {
        Reservation result = campsiteService.createReservation(reservation);
        assertNotNull(result.getId());
        campsiteService.deleteReservation(result.getId());
    }

    @Test
    public void testUpdateReservation()  {

        Reservation result = campsiteService.createReservation(reservation);
        result.getDeparture().plusDays(2);
        Reservation result2 = campsiteService.updateReservation(result.getId(), result);
        assertNotNull(result2.getId());
        campsiteService.deleteReservation(result2.getId());

    }

    @Test
    public void testDeleteReservation()  {

        Reservation result = campsiteService.createReservation(reservation);

        Reservation result2 = campsiteService.deleteReservation(result.getId());
        assertNotNull(result2.getId());
    }

    @Test
    public void testUpdateReservationException()  {
        Exception exception = assertThrows(DataNotFoundException.class, () -> {
            campsiteService.updateReservation("111111-11111", reservation);
        });
        String expectedMessage = "Cannot found reservation";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testCreateReservationException()  {
        Reservation result = campsiteService.createReservation(reservation);
        result.getDeparture().plusDays(2);
        Exception exception = assertThrows(DataProcessException.class, () -> {
            campsiteService.createReservation(result);
        });
        String expectedMessage = "Error on the reservation";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        campsiteService.deleteReservation(result.getId());
    }
}
