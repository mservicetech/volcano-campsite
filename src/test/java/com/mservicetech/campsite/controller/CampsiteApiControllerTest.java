package com.mservicetech.campsite.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mservicetech.campsite.model.AvailableDates;
import com.mservicetech.campsite.model.Client;
import com.mservicetech.campsite.model.Reservation;
import com.mservicetech.campsite.service.CampsiteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CampsiteApiControllerTest {
    @MockBean
    private CampsiteService campsiteService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get /api/campsite success")
    void listCampsite() throws Exception {
        AvailableDates availableDates = new AvailableDates();
        availableDates.addDatelistItem(LocalDate.of(2021, 11, 4));
        availableDates.addDatelistItem(LocalDate.of(2021, 11, 6));
        availableDates.addDatelistItem(LocalDate.of(2021, 11, 7));
        LocalDate startDate = LocalDate.of(2021, 11, 4);
        LocalDate endDate = LocalDate.of(2021, 11, 7);
        doReturn(availableDates).when(campsiteService).getAvailableDates(startDate, endDate);

        mockMvc.perform(get("/api/campsite?startDate={startDate}&endDate={endDate}",
                startDate.toString(), endDate.toString()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.datelist", hasSize(3)))
                .andExpect(jsonPath("$.datelist[0]", is("2021-11-04")))
                .andExpect(jsonPath("$.datelist[1]", is("2021-11-06")))
                .andExpect(jsonPath("$.datelist[2]", is("2021-11-07")));

    }

    @Test
    @DisplayName("Post /api/campsite success")
    void createOrder() throws Exception {
        Client client = new Client();
        client.setName("test");
        client.setEmail("test.admin@gmail.com");
        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setArrival(LocalDate.now().plusDays(1));
        reservation.setDeparture(LocalDate.now().plusDays(3));
        doReturn(reservation).when(campsiteService).createReservation(reservation);

        mockMvc.perform(
                post("/api/campsite")
                .content(asJsonString(reservation))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Put /api/campsite success")
    void updateCampsiteOrder() throws Exception {
        Client client = new Client();
        client.setName("test");
        client.setEmail("test.admin@gmail.com");
        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setArrival(LocalDate.now().plusDays(1));
        reservation.setDeparture(LocalDate.now().plusDays(3));
        doReturn(reservation).when(campsiteService).updateReservation("111111", reservation);

        mockMvc.perform(
                put("/api/campsite/111111")
                        .content(asJsonString(reservation))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete /api/campsite success")
    void deleteOrder() throws Exception {
        Client client = new Client();
        client.setName("test");
        client.setEmail("test.admin@gmail.com");
        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setArrival(LocalDate.now().plusDays(1));
        reservation.setDeparture(LocalDate.now().plusDays(3));
        doReturn(reservation).when(campsiteService).deleteReservation("111111");

        mockMvc.perform(
                delete("/api/campsite/111111")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Post /api/campsite success")
    void createOrderWithValidationError() throws Exception {
        Client client = new Client();
        client.setName("test");
        client.setEmail("test.admin@gmail.com");
        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setArrival(LocalDate.now().plusDays(1));
        reservation.setDeparture(LocalDate.now().plusDays(6));
        doReturn(reservation).when(campsiteService).createReservation(reservation);

        mockMvc.perform(
                post("/api/campsite")
                        .content(asJsonString(reservation))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
