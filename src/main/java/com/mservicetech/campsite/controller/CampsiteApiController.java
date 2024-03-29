package com.mservicetech.campsite.controller;

import com.mservicetech.campsite.exception.BusinessValidationException;
import com.mservicetech.campsite.model.AvailableDates;
import com.mservicetech.campsite.model.Error;
import com.mservicetech.campsite.model.Reservation;
import com.mservicetech.campsite.service.CampsiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.constraints.DecimalMax;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-10-28T21:30:05.649560600-04:00[America/New_York]")
@Controller
@RequestMapping("${openapi.swaggerVolcanoCampsite.base-path:/api}")
public class CampsiteApiController implements CampsiteApi {

    private final  String INVALID_DATE_RANGE = "ERR20001";
    private final  String INVALID_DATE_INPUT = "ERR20002";
    private final  String OVER_LIMIT = "ERR20002";
    private final NativeWebRequest request;
    private final CampsiteService campsiteService;

    @org.springframework.beans.factory.annotation.Autowired
    public CampsiteApiController(NativeWebRequest request, CampsiteService campsiteService) {
        this.request = request;
        this.campsiteService = campsiteService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<AvailableDates> listCampsite(LocalDate startDate, LocalDate endDate) {
        if (startDate==null) startDate = LocalDate.now().plusDays(1);
        if (endDate==null) endDate = LocalDate.now().plusDays(30);
        List<Error> errors  = validateSearchCritiria(startDate, endDate);
        if (errors.size()>0) {
            throw new BusinessValidationException(errors, "Business Validation error.");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(campsiteService.getAvailableDates(startDate, endDate));
    }

    @Override
    public ResponseEntity<Reservation> createOrder(Reservation reservation) {
        List<Error> errors  = validateReservation(reservation);
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
    public ResponseEntity<Reservation> getOrder(String orderId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(campsiteService.getReservation(orderId));
    }

    @Override
    public ResponseEntity<Reservation> updateCampsiteOrder(String orderId, Reservation reservation) {
        List<Error> errors  = validateReservation(reservation);
        if (errors.size()>0) {
            throw new BusinessValidationException(errors, "Business Validation error.");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(campsiteService.updateReservation(orderId, reservation));
    }

    private List<Error>  validateReservation(Reservation reservation)  {
        List<Error> errors = new ArrayList<>();
        if (reservation.getArrival().isBefore(LocalDate.now().plusDays(1)) || reservation.getDeparture().isAfter(LocalDate.now().plusDays(30))) {
            errors.add(new Error().code(INVALID_DATE_RANGE).message( "Invalid date range. Campsite only available from tomorrow to 30 days in advance."));
        }
        if (reservation.getDeparture().isBefore(reservation.getArrival())) {
            errors.add(new Error().code(INVALID_DATE_INPUT).message( "Invalid date input. Please verify again."));
        }
        if (Duration.between(reservation.getArrival().atStartOfDay(), reservation.getDeparture().atStartOfDay() ).toDays()>3) {
            errors.add(new Error().code(OVER_LIMIT).message( "The campsite can be reserved for max 3 days "));
        }
        return errors;
    }

    private List<Error>  validateSearchCritiria(LocalDate startDate, LocalDate endDate)  {
        List<Error> errors = new ArrayList<>();
        if (startDate.isBefore(LocalDate.now().plusDays(1)) || endDate.isAfter(LocalDate.now().plusDays(31)) || endDate.isBefore(startDate)) {
            errors.add(new Error().code(INVALID_DATE_RANGE).message( "Invalid date range. Campsite only available from tomorrow to 30 days in advance."));
        }
        return errors;
    }
}
