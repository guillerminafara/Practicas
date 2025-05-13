package com.example.ludoteca.rent;

import com.example.ludoteca.rent.model.Rent;
import com.example.ludoteca.rent.model.RentDto;
import com.example.ludoteca.rent.model.RentSearchDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Tag(name = "Rent", description = "API of Rent")
@RequestMapping(value = "/rent")
@RestController
@CrossOrigin(origins = "*")
public class RentController {
    @Autowired
    private RentService rentService;

    @Autowired
    ModelMapper mapper;

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Operation(summary = "Find Page", description = "Method that return a page of Rents")
    public Page<RentDto> find(@RequestBody RentSearchDto dto,
                              @RequestParam(name = "customerId", required = false) Long customerId,
                              @RequestParam(name = "gameId", required = false) Long gameId,
                              @RequestParam(name = "initialDay", required = false) LocalDate initialDay
    ) {
        Page<Rent> page = this.rentService.findPage(dto, customerId, gameId, initialDay);
        return new PageImpl<>(page.getContent()
                .stream().map(e -> mapper.map(e, RentDto.class))
                .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }

    @Operation(summary = "Save or Update", description = "Method that saves a Rent")
    @RequestMapping(path = {""}, method = RequestMethod.PUT)
    public void save(@RequestBody RentDto dto) {
        this.rentService.save(dto);
    }

    @Operation(summary = "Delete", description = "Method that deletes a Rent")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {
        this.rentService.delete(id);
    }
}
