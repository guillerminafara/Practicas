package com.example.ludoteca.rent;

import com.example.ludoteca.common.criteria.SearchCriteria;
import com.example.ludoteca.rent.model.Rent;
import com.example.ludoteca.rent.model.RentSearchDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RentServiceImpl implements RentService {
    @Autowired
    RentRepository rentRepository;

//    public List<Rent> find(Long customer_id,  Long game_id) {
////        RentSpecification customerSpec = new RentSpecification(new SearchCriteria("customer_id", ":", customer_id));
////        RentSpecification gameSpec= new RentSpecification(new SearchCriteria("game_id", ":", game_id));
////
////        Specification<Rent> spec= Specification.where(customerSpec).and(gameSpec);
////        return this.rentRepository.find(spec);
//    }

    @Override
    public Page<Rent> findPage(RentSearchDto dto) {
        return null;
    }

    @Override
    public List<Rent> findAll() {
        return (List<Rent>)this.rentRepository.findAll();
    }


//    @Override
//    public Page<Rent> findPage(RentSearchDto dto) {
//        return this.rentRepository.find(dto.getPageable().getPageable());
//    }
}
