package com.mservicetech.campsite.repository.mapper;

import com.mservicetech.campsite.model.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRowMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        Client client = new Client();
        client.setName(rs.getString("full_name"));
        client.setEmail(rs.getString("email"));
        client.setId(rs.getLong("id"));
        return client;
    }
}
