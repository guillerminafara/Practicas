package com.example.ludoteca.rent;

import com.example.ludoteca.rent.model.Rent;
import com.example.ludoteca.rent.model.RentSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RentService {
    // List<Rent> find(Long customer_id,  Long game_id);

   Page<Rent> findPage(RentSearchDto dto, Long id);

//    Page<Rent> find(RentSearchDto dto, @RequestParam Long customer_id);


    List<Rent> findAll();
}
