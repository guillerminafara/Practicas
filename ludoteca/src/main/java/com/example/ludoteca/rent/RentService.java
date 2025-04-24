package com.example.ludoteca.rent;

import com.example.ludoteca.rent.model.Rent;
import com.example.ludoteca.rent.model.RentSearchDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RentService{
   List<Rent> findAll();
   Page<Rent> findPage(RentSearchDto dto);
}
