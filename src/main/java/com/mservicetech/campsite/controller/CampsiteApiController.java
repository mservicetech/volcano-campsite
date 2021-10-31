package com.mservicetech.campsite.controller;

import com.mservicetech.campsite.exception.BusinessValidationException;
import com.mservicetech.campsite.model.AvailableDates;
import com.mservicetech.campsite.model.Error;
import com.mservicetech.campsite.model.Reservation;
import com.mservicetech.campsite.service.CampsiteService;
import com.mservicetech.campsite.validation.BaseValidator;
import com.mservicetech.campsite.validation.ValidationResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.constraints.DecimalMax;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-10-28T21:30:05.649560600-04:00[America/New_York]")
@Controller
@RequestMapping("${openapi.swaggerVolcanoCampsite.base-path:/api}")
public class CampsiteApiController implements CampsiteApi {

    private final NativeWebRequest request;
    private final CampsiteService campsiteService;
    private final  String SEARCH = "SEARCH";
    public  final String RESERVATION = "RESERVATION";

    private List<BaseValidator> validators;

    @org.springframework.beans.factory.annotation.Autowired
    public CampsiteApiController(NativeWebRequest request, CampsiteService campsiteService, List<BaseValidator> validators) {
        this.request = request;
        this.campsiteService = campsiteService;
        this.validators = validators;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<AvailableDates> listCampsite(LocalDate startDate, LocalDate endDate) {
        if (startDate==null) startDate = LocalDate.now().plusDays(1);
        if (endDate==null) endDate = LocalDate.now().plusDays(30);

        Map<String, LocalDate> searchCriteria = new HashMap<>();
        searchCriteria.put("startDate", startDate);
        searchCriteria.put("endDate", endDate);
        List<Error> errors = new ArrayList<>();
        Stream<ValidationResult> validationResultStream = validators.stream().filter(validator->validator.support(SEARCH)).flatMap(v->v.validate(null,searchCriteria));

        validationResultStream.filter(v->v.isError()).forEach(v->errors.addAll(v.getErrorCodes()));

        if (errors.size()>0) {
            throw new BusinessValidationException(errors, "Business Validation error.");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(campsiteService.getAvailableDates(startDate, endDate));
    }

    @Override
    public ResponseEntity<Reservation> createOrder(Reservation reservation) {
        List<Error> errors = new ArrayList<>();
        Stream<ValidationResult> validationResultStream = validators.stream().filter(validator->validator.support(RESERVATION)).flatMap(v->v.validate(null,reservation));
        validationResultStream.filter(v->v.isError()).forEach(v->errors.addAll(v.getErrorCodes()));

        if (errors.size()>0) {
            throw new BusinessValidationException(errors, "Business Validation error.");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(campsiteService.createReservation(reservation));
    }

    @Override
    public ResponseEntity<Reservation> deleteOrder(String orderId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(campsiteService.deleteReservation(orderId));
    }

    @Override
    public ResponseEntity<Reservation> updateCampsiteOrder(String orderId, Reservation reservation) {
        List<Error> errors = new ArrayList<>();
        Stream<ValidationResult> validationResultStream = validators.stream().filter(validator->validator.support(RESERVATION)).flatMap(v->v.validate(null,reservation));
        validationResultStream.filter(v->v.isError()).forEach(v->errors.addAll(v.getErrorCodes()));

        if (errors.size()>0) {
            throw new BusinessValidationException(errors, "Business Validation error.");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(campsiteService.updateReservation(orderId, reservation));
    }

}
