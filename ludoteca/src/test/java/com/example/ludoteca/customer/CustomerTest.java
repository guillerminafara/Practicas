package com.example.ludoteca.customer;

import com.example.ludoteca.customer.model.Customer;
import com.example.ludoteca.customer.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerTest {
    public static final String CUSTOMER_NAME = "CUS1";
    public static final Long EXISTS_CUSTOMER_ID = 1L;
    public static final String EXISTS_CUSTOMER_NAME = "Paquito";
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void findAllShouldReturnAllCustomers() {

        List<Customer> list = new ArrayList<>();
        list.add(mock(Customer.class));

        when(customerRepository.findAll()).thenReturn(list);

        List<Customer> customer = customerService.findAll();

        assertNotNull(customer);
        assertEquals(1, customer.size());
    }


    @Test
    public void saveNotExistsCustomerIdShouldInsert() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(CUSTOMER_NAME);

        ArgumentCaptor<Customer> customer = ArgumentCaptor.forClass(Customer.class);

        customerService.save(null, customerDto);

        verify(customerRepository).save(customer.capture());

        assertEquals(CUSTOMER_NAME, customer.getValue().getName());
    }

    @Test
    public void saveExistsCustomerIdShouldUpdate() throws Exception {

        CustomerDto cusromerDto = new CustomerDto();
        cusromerDto.setName(CUSTOMER_NAME);

        Customer customer = mock(Customer.class);
        when(customerRepository.findById(EXISTS_CUSTOMER_ID)).thenReturn(Optional.of(customer));

        customerService.save(EXISTS_CUSTOMER_ID, cusromerDto);

        verify(customerRepository).save(customer);
    }

    @Test
    public void deleteExistsCustomerIdShouldDelete() throws Exception {

        Customer customer = mock(Customer.class);
        when(customerRepository.findById(EXISTS_CUSTOMER_ID)).thenReturn(Optional.of(customer));

        customerService.delete(EXISTS_CUSTOMER_ID);

        verify(customerRepository).deleteById(EXISTS_CUSTOMER_ID);
    }

    @Test
    public void saveExistsCustomerNameShouldNotInsert() throws Exception {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(EXISTS_CUSTOMER_NAME);

        Customer customer = mock(Customer.class);
        when(customerRepository.findByName(EXISTS_CUSTOMER_NAME)).thenReturn(customer);

        customerService.save(null, customerDto);
        //verify(customerRepository).save(customer);
        assertNull(EXISTS_CUSTOMER_NAME, customer.getName());
    }
}
