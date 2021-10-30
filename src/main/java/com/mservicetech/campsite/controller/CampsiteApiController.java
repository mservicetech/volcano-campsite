package com.mservicetech.campsite.controller;

import com.mservicetech.campsite.model.AvailableDates;
import com.mservicetech.campsite.model.Reservation;
import com.mservicetech.campsite.service.CampsiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.time.LocalDate;
import java.util.Optional;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-10-28T21:30:05.649560600-04:00[America/New_York]")
@Controller
@RequestMapping("${openapi.swaggerVolcanoCampsite.base-path:/api}")
public class CampsiteApiController implements CampsiteApi {

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
        return ResponseEntity.status(HttpStatus.OK)
                .body(campsiteService.getAvailableDates(startDate, endDate));
    }

    @Override
    public ResponseEntity<Reservation> createOrder(Reservation reservation) {
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
        return ResponseEntity.status(HttpStatus.OK)
                .body(campsiteService.updateReservation(orderId, reservation));
    }
}
