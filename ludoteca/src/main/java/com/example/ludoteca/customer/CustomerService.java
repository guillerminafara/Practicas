package com.example.ludoteca.customer;

import com.example.ludoteca.customer.model.Customer;
import com.example.ludoteca.customer.model.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<Customer>findAll();
    void save(Long id, CustomerDto dto)throws Exception;
    void delete(Long id) throws Exception;
    Customer get(Long id);
}
