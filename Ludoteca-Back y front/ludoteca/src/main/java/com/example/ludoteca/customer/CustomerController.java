package com.example.ludoteca.customer;

import com.example.ludoteca.category.model.Category;
import com.example.ludoteca.customer.model.Customer;
import com.example.ludoteca.customer.model.CustomerDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "Customer", description = "API of Category")
@RequestMapping(value = "/customer")
@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    ModelMapper mapper;

    @Operation(summary = "Find", description = "Method that return a list of Customers")
    @RequestMapping(path = {""}, method = RequestMethod.GET)
    public List<CustomerDto> findAll() {
        List<Customer> customer = this.customerService.findAll();
        return customer.stream().map(e -> mapper.map(e, CustomerDto.class)).collect(Collectors.toList());
    }

    @Operation(summary = "Save or Update", description = "Method that saves or updates a customer")
    @RequestMapping(path = {"", "/{id}"}, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody CustomerDto dto) throws Exception {
        this.customerService.save(id, dto);

    }

    @Operation(summary = "Delete", description = "Method that deletes a Customer")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {

        this.customerService.delete(id);
    }
}
