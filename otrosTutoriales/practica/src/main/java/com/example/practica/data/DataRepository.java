package com.example.practica.data;

public interface DataRepository {
    Person jdbcQueryObject(Long id);
}
