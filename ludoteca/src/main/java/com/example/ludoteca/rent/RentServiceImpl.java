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
    public Page<Rent> findPage(RentSearchDto dto, Long idCustomer, Long idGame) {
        Pageable pageable = dto.getPageable().getPageable();
        Specification<Rent> spec = Specification.where(null);
        if (idCustomer != null) {
            RentSpecification customerIdSpec = new RentSpecification(new SearchCriteria("customer_id", ":", idCustomer));
            spec = spec.and(customerIdSpec);
        }
        if (idGame != null) {
            RentSpecification gameIdSpec = new RentSpecification(new SearchCriteria("customer_id", ":", idGame));
            spec = spec.and(gameIdSpec);
        }

        return this.rentRepository.findAll(spec, pageable);
    }


//    @Override
//    public List<Rent> findAll() {
//        return (List<Rent>) this.rentRepository.findAll();
//    }
//

}
