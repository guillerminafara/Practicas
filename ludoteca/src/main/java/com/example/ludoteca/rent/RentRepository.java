package com.example.ludoteca.rent;

import com.example.ludoteca.rent.model.Rent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface RentRepository extends CrudRepository<Rent, Long> {
    Page<Rent>findAll(Pageable pageable);
}
