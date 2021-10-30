package com.mservicetech.campsite.service;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Test
    public void test2()  {
        long days = Duration.between(LocalDate.now().plusDays(4).atStartOfDay(), LocalDate.now().plusDays(1).atStartOfDay() ).toDays();
        System.out.println(days);

    }
}
