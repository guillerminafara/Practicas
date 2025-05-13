package com.example.ludoteca.rent;

import com.example.ludoteca.rent.model.Rent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface RentRepository extends CrudRepository<Rent, Long>, JpaSpecificationExecutor<Rent> {
    @Override
    @EntityGraph(attributePaths = {"customer", "game", "initialDate", "endDate"})
    Page<Rent> findAll(Specification<Rent> spec, Pageable pageable);
}
