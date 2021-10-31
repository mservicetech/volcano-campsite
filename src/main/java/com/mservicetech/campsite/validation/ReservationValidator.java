package com.mservicetech.campsite.validation;


import com.mservicetech.campsite.model.Error;
import com.mservicetech.campsite.model.Reservation;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class ReservationValidator implements BaseValidator<String, Reservation> {

    private final  String INVALID_DATE_RANGE = "ERR20001";
    private final  String INVALID_DATE_INPUT = "ERR20002";
    private final  String OVER_LIMIT = "ERR20002";
    public  final String RESERVATION_FILTER = "RESERVATION";

    @Override
    public boolean support(String filter) {
        return RESERVATION_FILTER.equalsIgnoreCase(filter);
    }

    @Override
    public Stream<ValidationResult> validate(Object context, Reservation reservation) {
        List<ValidationResult> validationResults  = new ArrayList<>();
        ValidationResult validationResult = new ValidationResult(ReservationValidator.class);
        if (reservation.getArrival().isBefore(LocalDate.now().plusDays(1)) || reservation.getDeparture().isAfter(LocalDate.now().plusDays(30))) {
            validationResult.setError(true);
            validationResult.addErrorCodes(new Error().code(INVALID_DATE_RANGE).message("Invalid date range. Campsite only available from tomorrow to 30 days in advance."));
        }
        if (reservation.getDeparture().isBefore(reservation.getArrival())) {
            validationResult.setError(true);
            validationResult.addErrorCodes(new Error().code(INVALID_DATE_INPUT).message( "Invalid date input. Please verify again."));
        }
        if (Duration.between(reservation.getArrival().atStartOfDay(), reservation.getDeparture().atStartOfDay() ).toDays()>3) {
            validationResult.setError(true);
            validationResult.addErrorCodes(new Error().code(OVER_LIMIT).message( "The campsite can be reserved for max 3 days "));
        }
        validationResults.add(validationResult);
        return validationResults.stream();
    }
}
