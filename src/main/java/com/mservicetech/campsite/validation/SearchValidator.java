package com.mservicetech.campsite.validation;


import com.mservicetech.campsite.model.Error;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class SearchValidator implements BaseValidator<String, Map<String, LocalDate>> {

    private final  String INVALID_DATE_RANGE = "ERR20001";
    public  final String SEARCH_FILTER = "SEARCH";

    @Override
    public boolean support(String filter) {
        return SEARCH_FILTER.equalsIgnoreCase(filter);
    }

    @Override
    public Stream<ValidationResult> validate(Object context, Map<String, LocalDate> searchCriteria) {
        List<ValidationResult> validationResults  = new ArrayList<>();
        LocalDate startDate = searchCriteria.get("startDate");
        LocalDate endDate = searchCriteria.get("endDate");
        ValidationResult validationResult = new ValidationResult(SearchValidator.class);
        if (startDate.isBefore(LocalDate.now().plusDays(1)) || endDate.isAfter(LocalDate.now().plusDays(31)) || endDate.isBefore(startDate)) {
            validationResult.setError(true);
            validationResult.addErrorCodes(new Error().code(INVALID_DATE_RANGE).message("Invalid date range. Campsite only available from tomorrow to 30 days in advance."));
        }
        return validationResults.stream();
    }
}
