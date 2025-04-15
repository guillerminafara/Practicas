package com.example.practica.data;

import java.beans.BeanProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class DataRepositoryImpl implements DataRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Person jdbcQueryObject(Long id) {

        String sql= "select id, name, role from person where id = "+ id;
        // RowMapper<Person> rowMapper = new PersonRowMapper();
        BeanPropertyRowMapper<Person> rowMapper= new BeanPropertyRowMapper<Person>(Person.class);

        return jdbcTemplate.queryForObject(sql, rowMapper);
    }
}
