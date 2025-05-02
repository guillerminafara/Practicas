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


    @Override
    public Page<Rent> findPage(RentSearchDto dto, Long customerId, Long gameId, String dateSelectedDay) {
        Specification<Rent> spec = Specification.where(null);
//
//        RentSpecification customerIdSpec = new RentSpecification(new SearchCriteria("customer_id", ":", customerId));
//        RentSpecification gameIdSpec = new RentSpecification(new SearchCriteria("game_id", ":", gameId));

//        Specification<Rent> spec = Specification.where(customerIdSpec).and(gameIdSpec);
        if(customerId!=null && gameId != null){
            RentSpecification customerIdSpec = new RentSpecification(new SearchCriteria("customer.id", ":", customerId));
            RentSpecification gameIdSpec = new RentSpecification(new SearchCriteria("game.id", ":", gameId));
            spec = spec.and(customerIdSpec).and(gameIdSpec);
        }else{
            if (customerId != null) {
                RentSpecification customerIdSpec = new RentSpecification(new SearchCriteria("customer.id", ":", customerId));
                spec = spec.and(customerIdSpec);
            }
            if (gameId != null) {
                RentSpecification gameIdSpec = new RentSpecification(new SearchCriteria("game.id", ":", gameId));
                spec = spec.and(gameIdSpec);
            }
            if(dateSelectedDay != null){
               RentSpecification dateSelectedDay1= new RentSpecification(new SearchCriteria("initialDate", ":", dateSelectedDay));
               spec=spec.and(dateSelectedDay1);
            }

        }

        return this.rentRepository.findAll(spec, dto.getPageable().getPageable());
    }


//    @Override
//    public List<Rent> findAll() {
//        return (List<Rent>) this.rentRepository.findAll();
//    }
//

}
