package com.example.practica.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DataJpaRepository extends JpaRepository<Person, Long> {
    Person getById(Long id);

    Person getbyNameAndRoleOrderByName(String name, String role);
}
