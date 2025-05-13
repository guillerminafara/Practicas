package com.example.ludoteca.customer;

import com.example.ludoteca.customer.model.Customer;
import com.example.ludoteca.customer.model.CustomerDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return (List<Customer>) this.customerRepository.findAll();
    }

    @Override
    public void save(Long id, CustomerDto dto) throws Exception {
        Customer customer;
        if (customerRepository.findByName(dto.getName()) == null) {
            if (id == null) {
                customer = new Customer();
            } else {
                customer = this.get(id);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente repetido");
        }
        customer.setName(dto.getName());
        this.customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) throws Exception {
        if (this.customerRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not Exists");
        }
        this.customerRepository.deleteById(id);
    }

    /**
     * @param id
     * @return a Customer
     */
    @Override
    public Customer get(Long id) {
        return this.customerRepository.findById(id).orElse(null);
    }
}
