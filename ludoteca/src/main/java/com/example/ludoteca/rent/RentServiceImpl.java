package com.example.ludoteca.rent;

import com.example.ludoteca.author.model.Author;

import com.example.ludoteca.rent.model.Rent;
import com.example.ludoteca.rent.model.RentSearchDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RentServiceImpl implements RentService {
    @Autowired
    RentRepository rentRepository;

    public List<Rent> findAll(){
        return(List<Rent>)this.rentRepository.findAll();
    }

    @Override
    public Page<Rent> findPage(RentSearchDto dto) {
        return this.rentRepository.findAll(dto.getPageable().getPageable());
    }
}
