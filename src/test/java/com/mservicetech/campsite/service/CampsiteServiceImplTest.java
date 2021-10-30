package com.mservicetech.campsite.service;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CampsiteServiceImplTest {

    @BeforeAll
    public static void setUp() {

    }



    @Test
    public void testTimeDuration()  {
        LocalDate today = LocalDate.now();
        LocalDate future = today.plusDays(30);
        long result = Duration.between(today.atStartOfDay(), future.atStartOfDay() ).toDays();
        assertEquals(result, 30);
    }


}
