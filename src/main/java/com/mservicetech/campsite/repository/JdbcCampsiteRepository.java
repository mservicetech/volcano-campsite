package com.mservicetech.campsite.repository;

import com.mservicetech.campsite.model.Client;
import com.mservicetech.campsite.model.Reservation;
import com.mservicetech.campsite.repository.mapper.ClientRowMapper;
import com.mservicetech.campsite.repository.mapper.ReservationRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
public class JdbcCampsiteRepository implements CampsiteRepository{

    private static final Logger logger= LoggerFactory.getLogger(CampsiteRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${query.getReservedDates}")
    private String QUERY_GET_ALL_RESERVED;
    @Value("${query.insertReservedDates}")
    private String QUERY_INSERT_RESERVED_DATE;
    @Value("${query.getClientByEmail}")
    private String QUERY_GET_CLIENT_BY_EMAIL;
    @Value("${query.insertClient}")
    private String QUERY_INSERT_CLIENT;
    @Value("${query.insertReservation}")
    private String QUERY_INSERT_RESERVATION;
    @Value("${query.deleteReservedDates}")
    private String QUERY_DELETE_RESERVED_DATE;
    @Value("${query.getReservation}")
    private String QUERY_GET_RESERVATION;
    @Value("${query.deleteReservation}")
    private String QUERY_DELETE_RESERVATION;
    @Value("${query.updateReservation}")
    private String QUERY_UPDATE_RESERVATION;

    @Override
    public List<LocalDate> findReserved() {
        if (logger.isDebugEnabled()) logger.debug("query for get date list:" + QUERY_GET_ALL_RESERVED);
        return jdbcTemplate.query(
                QUERY_GET_ALL_RESERVED,
                (rs, rowNum) ->rs.getDate("reserved_date").toLocalDate()

        );
    }

    @Override
    public int reserve(Reservation reservation) {
        return 0;
    }

    @Override
    public int reserveDates(List<LocalDate> dateList) {
        int[] rows =  jdbcTemplate.batchUpdate(QUERY_INSERT_RESERVED_DATE, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                LocalDate localDate = dateList.get(i);
                ps.setDate(1, Date.valueOf(localDate));
            }
            @Override
            public int getBatchSize() {
                return dateList.size();
            }
        });
        return Arrays.stream(rows).sum();
    }

    @Override
    public List<Client> checkClientExisting(Client client) {
        if (logger.isDebugEnabled()) logger.debug("query for get CLIENT:" + QUERY_GET_CLIENT_BY_EMAIL);
        return jdbcTemplate.query(QUERY_GET_CLIENT_BY_EMAIL,  new ClientRowMapper(), client.getEmail());
    }

    @Override
    public long insertClient(Client client) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(QUERY_INSERT_CLIENT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, client.getName());
            ps.setString(2,client.getEmail());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public String createReservation(Reservation reservation) {
        String reservationId = UUID.randomUUID().toString();
        jdbcTemplate.update(
                QUERY_INSERT_RESERVATION, reservationId,
                reservation.getClient().getId(), reservation.getArrival(), reservation.getDeparture());
        return reservationId;
    }

    @Override
    public int deleteDates(List<LocalDate> dateList) {
        int[] rows =  jdbcTemplate.batchUpdate(QUERY_DELETE_RESERVED_DATE, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                LocalDate localDate = dateList.get(i);
                ps.setDate(1, Date.valueOf(localDate));
            }
            @Override
            public int getBatchSize() {
                return dateList.size();
            }
        });
        return Arrays.stream(rows).sum();
    }

    @Override
    public Reservation getReservation(String reservationId) {
        if (logger.isDebugEnabled()) logger.debug("query for get CLIENT:" + QUERY_GET_CLIENT_BY_EMAIL);
        return jdbcTemplate.queryForObject(QUERY_GET_RESERVATION,  new ReservationRowMapper(), reservationId);
    }

    @Override
    public int deleteReservation(String reservationId) {
        return jdbcTemplate.update(QUERY_DELETE_RESERVATION, reservationId);
    }

    @Override
    public int updateReservation(Reservation reservation) {
        return jdbcTemplate.update(QUERY_UPDATE_RESERVATION, Date.valueOf(reservation.getArrival()), Date.valueOf(reservation.getDeparture()), reservation.getId());
    }
}
