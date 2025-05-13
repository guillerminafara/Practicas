package com.example.ludoteca.rent;

import com.example.ludoteca.rent.model.Rent;
import com.example.ludoteca.rent.model.RentDto;
import com.example.ludoteca.rent.model.RentSearchDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface RentService {
    Page<Rent> findPage(RentSearchDto dto, Long customerId, Long gameId, LocalDate dateSelectedDay);

    void save(RentDto dto);

    void delete(Long id) throws Exception;
}
