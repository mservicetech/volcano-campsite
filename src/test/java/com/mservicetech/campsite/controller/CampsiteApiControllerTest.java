package com.mservicetech.campsite.controller;

import com.mservicetech.campsite.model.AvailableDates;
import com.mservicetech.campsite.service.CampsiteService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Disabled
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
}
