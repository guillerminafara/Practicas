package com.example.ludoteca.rent;

import com.example.ludoteca.rent.model.Rent;
import com.example.ludoteca.rent.model.RentSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface RentService {

   Page<Rent> findPage(RentSearchDto dto, Long customerId, Long gameId, LocalDate dateSelectedDay);
//   List<Rent> findAll();
}
