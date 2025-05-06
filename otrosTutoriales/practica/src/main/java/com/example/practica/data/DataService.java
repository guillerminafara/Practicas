package com.example.practica.data;

public interface DataService {
    Person jdbcQueryObject(Long id);
    Person jpaQueryObject(Long id);
    Person jpaInsertObject(String name, String role);
    void jpaInsertDummy();

}
