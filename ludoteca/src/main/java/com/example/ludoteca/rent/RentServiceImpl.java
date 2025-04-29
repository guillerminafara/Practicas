package com.example.ludoteca.rent;

import com.example.ludoteca.common.criteria.SearchCriteria;
import com.example.ludoteca.rent.model.Rent;
import com.example.ludoteca.rent.model.RentSearchDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@Transactional
public class RentServiceImpl implements RentService {
    @Autowired
    RentRepository rentRepository;

//    @Override
//    public Page<Rent> find(RentSearchDto dto, @RequestParam Long customer_id) {
//        RentSpecification customerSpec = new RentSpecification(new SearchCriteria("customer_id", ":", customer_id));
//       // RentSpecification gameSpec= new RentSpecification(new SearchCriteria("game_id", ":", game_id));
//
//        Specification<Rent> spec= Specification.where(customerSpec);
//        return this.rentRepository.find(spec,dto.getPageable().getPageable());
//    }


    @Override
    public Page<Rent> findPage(RentSearchDto dto, Long id) {
        System.out.println("Id: "+ id);
       Pageable pageable= dto.getPageable().getPageable();
        RentSpecification customerIdSpec = new RentSpecification(new SearchCriteria("customer_id", ":", id));
        Specification<Rent> spec= Specification.where(customerIdSpec);

        return this.rentRepository.findAll(spec, pageable);
    }



    @Override
    public List<Rent> findAll() {
        return (List<Rent>)this.rentRepository.findAll();
    }


}
