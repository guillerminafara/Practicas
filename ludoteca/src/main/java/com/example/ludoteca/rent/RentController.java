package com.example.ludoteca.rent;

import com.example.ludoteca.author.model.Author;
import com.example.ludoteca.author.model.AuthorDto;
import com.example.ludoteca.author.model.AuthorSearchDto;
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

import java.util.List;
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

//    @Operation(summary = "Find", description = "Method that return a list of all Rents")
//    @RequestMapping(path = "/", method = RequestMethod.POST)
//    public List<RentDto>findAll(){
//       List<Rent> rents= rentService.findAll();
//       return rents.stream().map(e -> mapper.map(e, RentDto.class)).collect(Collectors.toList());
//    }


    @RequestMapping(path = "", method = RequestMethod.POST)
    @Operation(summary = "Find Page", description = "Method that return a page of Rents")
    public Page<RentDto> find(@RequestBody RentSearchDto dto,
                                  @RequestParam(name="customerId", required = false) Long customerId,
                              @RequestParam(name="gameId", required = false) Long gameId,
                              @RequestParam (name="initialDay", required = false) String initialDay
    ) {

//        System.out.printf("------------------------------> %s",dto.getPageable().getPageable().toString());
        Page<Rent> page = this.rentService.findPage(dto, customerId, gameId, initialDay);

        return new PageImpl<>(page.getContent()
                .stream().map(e -> mapper.map(e, RentDto.class))
                .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }
}
