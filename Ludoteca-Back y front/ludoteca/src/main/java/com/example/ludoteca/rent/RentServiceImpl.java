package com.example.ludoteca.rent;

import com.example.ludoteca.common.criteria.SearchCriteria;
import com.example.ludoteca.customer.CustomerService;
import com.example.ludoteca.game.GameService;
import com.example.ludoteca.rent.model.Rent;
import com.example.ludoteca.rent.model.RentDto;
import com.example.ludoteca.rent.model.RentSearchDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@Transactional
public class RentServiceImpl implements RentService {
    @Autowired
    RentRepository rentRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    GameService gameService;

    /**
     * @param dto
     * @param customerId
     * @param gameId
     * @param dateSelectedDay
     * @return Devuelve una lista de alquileres paginable
     */
    @Override
    public Page<Rent> findPage(RentSearchDto dto, Long customerId, Long gameId, LocalDate dateSelectedDay) {
        Specification<Rent> spec = Specification.where(null);

        if (customerId != null) {
            RentSpecification customerIdSpec = new RentSpecification(new SearchCriteria("customer.id", ":", customerId));
            spec = spec.and(customerIdSpec);
        }
        if (gameId != null) {
            RentSpecification gameIdSpec = new RentSpecification(new SearchCriteria("game.id", ":", gameId));
            spec = spec.and(gameIdSpec);
        }
        if (dateSelectedDay != null) {
            RentSpecification dateSelectedInitialSpec = new RentSpecification(new SearchCriteria("initialDate", "<=", dateSelectedDay));
            RentSpecification dateSelectedFinalSpec = new RentSpecification(new SearchCriteria("endDate", ">=", dateSelectedDay));
            spec = spec.and(dateSelectedInitialSpec).and(dateSelectedFinalSpec);
        }
        return this.rentRepository.findAll(spec, dto.getPageable().getPageable());
    }

    /**
     * @param dto Método que guarda alquileres de jegos en la base de datos
     */
    @Override
    public void save(RentDto dto) {
        Rent rent = new Rent();
        rent.setCustomer(this.customerService.get(dto.getCustomer().getId()));
        rent.setGame(this.gameService.getGame(dto.getGame().getId()));
        rent.setEndDate(dto.getEndDate());
        rent.setInitialDate(dto.getInitialDate());

        Boolean rangeDate = validateGameRangeDate(dto);
        Boolean rentedGameDate = filterGameByDate(dto, dto.getGame().getId()); // true= no prestado
        Boolean customerRentals = countCustomersRents(dto); // true= no supera los 2 alquileres
        Boolean countRentalsDays = countRentDate(dto); // true= no supera los 14 diass

        if (rentedGameDate && countRentalsDays && rangeDate && customerRentals) {// si el juego en esos dias no eta conectado
            BeanUtils.copyProperties(dto, rent, "id");
            this.rentRepository.save(rent);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        if (rentRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not Exists");
        }
        this.rentRepository.deleteById(id);
    }

    /**
     * @param dto
     * @param gameId
     * @return true si el juego ha sido alquilado entre esos días.
     * @throws ResponseStatusException
     */
    public Boolean filterGameByDate(RentDto dto, Long gameId) {
        LocalDate initialDate = dto.getInitialDate();
        LocalDate endDate = dto.getEndDate();
        Boolean bandera = false;
        Specification initialDateSpec = new RentSpecification(new SearchCriteria("initialDate", "<=", endDate));
        Specification endDateSpec = new RentSpecification(new SearchCriteria("endDate", ">=", initialDate));
        Specification<Rent> gamesRented = new RentSpecification(new SearchCriteria("game.id", ":", gameId))
                .and(initialDateSpec)
                .and(endDateSpec);
        long rents = this.rentRepository.count(gamesRented);
        if (rents == 0) {
            bandera = true;
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El juego ya se encuentra alquilado");
        }
        return bandera;
    }

    /**
     * @param dto
     * @return true si el cliente no ha superado el máximo de alquileres
     * @throws ResponseStatusException
     */
    public Boolean countCustomersRents(RentDto dto) {
        Boolean bandera = true;
        Specification<Rent> customerRentals = new RentSpecification(
                new SearchCriteria("customer.id", ":", dto.getCustomer().getId()))
                .and(new RentSpecification(new SearchCriteria("initialDate", "<=", dto.getEndDate())))
                .and(new RentSpecification(new SearchCriteria("endDate", ">=", dto.getInitialDate())));
        if (this.rentRepository.count(customerRentals) >= 2) {
            bandera = false;
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El cliente se encuentra en el máximo de alquileres");
        }
        return bandera;
    }

    /**
     * @param dto
     * @return true si no ha superado los 14 de alquiler
     * @throws ResponseStatusException
     */
    public Boolean countRentDate(RentDto dto) {
        boolean bandera = true;
        LocalDate start = dto.getInitialDate();
        LocalDate end = dto.getEndDate();

        if (start == null || end == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Las fechas de inicio y fin no pueden ser nulas");
        }
        long daysBetween = ChronoUnit.DAYS.between(start, end);
        if (daysBetween >= 14) {
            bandera = false;
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El alquiler no puede superar los 14 días");
        }
        return bandera;
    }

    /**
     * @param dto
     * @return true si la fecha de devolución es posterior a la fecha de inicio
     */
    private boolean validateGameRangeDate(RentDto dto) {
        return dto.getEndDate().isAfter(dto.getInitialDate());
    }

}
