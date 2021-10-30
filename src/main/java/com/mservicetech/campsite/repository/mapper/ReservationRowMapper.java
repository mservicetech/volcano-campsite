package com.mservicetech.campsite.repository.mapper;

import com.mservicetech.campsite.model.Client;
import com.mservicetech.campsite.model.Reservation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationRowMapper implements RowMapper<Reservation> {

    @Override
    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reservation reservation = new Reservation();
        Client client = new Client();
        client.setName(rs.getString("full_name"));
        client.setEmail(rs.getString("email"));
        client.setId(rs.getLong("client_id"));
        reservation.setId(rs.getString("id"));
        reservation.setArrival(rs.getDate("arrival_date").toLocalDate());
        reservation.setDeparture(rs.getDate("departure_date").toLocalDate());
        reservation.setClient(client);
        return reservation;
    }
}
