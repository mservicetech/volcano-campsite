package com.mservicetech.campsite.repository;

import com.mservicetech.campsite.model.Client;
import com.mservicetech.campsite.model.Reservation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@SpringBootTest
@ActiveProfiles("test")
public class CampsiteRepositoryTest {

    @Autowired
    private CampsiteRepository campsiteRepository;
    private static  Client client;
    private static Reservation reservation;

    @BeforeAll
    public static void setUp() {
        client = new Client();
        client.setEmail("volcano.admin@gmail.com");
        reservation = new Reservation();
        reservation.setArrival(LocalDate.now());
        reservation.setDeparture(LocalDate.now().plusDays(3));

    }

    @Test
    public void testFindReserved() {
        List<LocalDate> reservedList =  campsiteRepository.findReserved();
        assertTrue(reservedList.size()>0);
    }

    @Test
    public void testCheckUserExisting() {
        List<Client> existing =  campsiteRepository.checkClientExisting(client);
        assertTrue(existing.size()>0);
    }
    @Test
    public void testCheckUserNotExisting() {
        Client client = new Client();
        client.setEmail("test@volcano.com");
        List<Client> existing =  campsiteRepository.checkClientExisting(client);
        assertTrue(existing.size()==0);
    }

    @Test
    public void testInsertClient() {
        Client client = new Client();
        client.setName("Test Test");
        client.setEmail("Test.Test@volcano.com");
        long newClient =  campsiteRepository.insertClient(client);
        assertNotNull(newClient);
    }

    @Test
    public void testCreateReservation() {
        Client client = new Client();
        client.setName("Test Test");
        client.setEmail("Test.Test2@volcano.com");
        long newClient =  campsiteRepository.insertClient(client);
        client.setId(newClient);
        reservation.setClient(client);
        String reservationId =  campsiteRepository.createReservation(reservation);
        assertNotNull(reservationId);
    }

    @Test
    public void testReservedDates() {
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(LocalDate.now());
        dateList.add(LocalDate.now().plusDays(1));
        campsiteRepository.deleteDates(dateList);
        int records =  campsiteRepository.reserveDates(dateList);
        assertEquals(records, 2);
        records =  campsiteRepository.deleteDates(dateList);
        assertEquals(records, 2);
    }

    @Test
    public void testUpdateReservation() {
        Client client = new Client();
        client.setName("Test Test");
        client.setEmail("Test.Test3@volcano.com");
        long newClient =  campsiteRepository.insertClient(client);
        client.setId(newClient);
        reservation.setClient(client);
        String reservationId =  campsiteRepository.createReservation(reservation);
        assertNotNull(reservationId);
        reservation.setId(reservationId);
        reservation.setDeparture(LocalDate.now().plusDays(6));
        int rec = campsiteRepository.updateReservation(reservation);
        assertTrue(rec>0);
    }

    @Test
    public void testDeleteReservation() {
        Client client = new Client();
        client.setName("Test Test");
        client.setEmail("Test.Test4@volcano.com");
        long newClient =  campsiteRepository.insertClient(client);
        client.setId(newClient);
        reservation.setClient(client);
        String reservationId =  campsiteRepository.createReservation(reservation);
        assertNotNull(reservationId);
        int rec = campsiteRepository.deleteReservation(reservationId);
        assertTrue(rec>0);
    }

    @Test
    public void testVerifyDates() {
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(LocalDate.now());
        dateList.add(LocalDate.now().plusDays(1));
        campsiteRepository.deleteDates(dateList);
        int records =  campsiteRepository.reserveDates(dateList);
        assertEquals(records, 2);
        dateList.clear();
        dateList.add(LocalDate.now().plusDays(1));
        dateList.add(LocalDate.now().plusDays(2));
        dateList.add(LocalDate.now().plusDays(3));
        List<LocalDate> result =  campsiteRepository.verifyDates(dateList);
        assertEquals(result.size(), 1);
        campsiteRepository.deleteDates(result);
    }

    @Test
    public void testVerifyDatesEmpty() {
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(LocalDate.now().plusDays(1));
        dateList.add(LocalDate.now().plusDays(2));
        dateList.add(LocalDate.now().plusDays(3));
        campsiteRepository.deleteDates(dateList);
        List<LocalDate> result =  campsiteRepository.verifyDates(dateList);
        assertEquals(result.size(), 0);
    }
}
