package com.example.ludoteca.rent;

import com.example.ludoteca.rent.model.Rent;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RentRepository extends CrudRepository<Rent, Long> {
//    List<Rent> find(Specification<Rent>spec);
}
