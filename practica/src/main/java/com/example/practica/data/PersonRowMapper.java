package com.example.practica.data;

import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.sql.ResultSet;

public class PersonRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person= new Person();
        if(rs !=null){
            person.setId(rs.getLong("id"));
            person.setName(rs.getString("name"));
            person.setRole(rs.getString("role"));
        }
        return person;
    }
}
