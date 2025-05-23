package com.example.ludoteca.customer;

import com.example.ludoteca.customer.model.Customer;
import com.example.ludoteca.customer.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CustomerIT {
    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/customer";
    public static final Long NEW_CUSTOMER_ID = 10L;
    public static final String NEW_CUSTOMER_NAME = "CUS4";
    public static final Long MODIFY_CUSTOMER_ID = 3L;
    public static final Long DELETE_CUSTOMER_ID = 2L;
    public static final String EXISTS_CUSTOMER_NAME = "Paquito";


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<List<CustomerDto>> responseType = new ParameterizedTypeReference<List<CustomerDto>>(){};
    @Test
    public void findAllShouldReturnAllCustomers() {

        ResponseEntity<List<CustomerDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseType);

        assertNotNull(response);
        assertEquals(9, response.getBody().size());
    }

    @Test
    public void saveWithoutIdShouldCreateNewCustomer() {

        CustomerDto dto = new CustomerDto();
        dto.setName(NEW_CUSTOMER_NAME);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        ResponseEntity<List<CustomerDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(10, response.getBody().size());

        CustomerDto customerSearch = response.getBody().stream().filter(item -> item.getId().equals(NEW_CUSTOMER_ID)).findFirst().orElse(null);
        assertNotNull(customerSearch);
        assertEquals(NEW_CUSTOMER_NAME, customerSearch.getName());
    }

    @Test
    public void modifyWithExistIdShouldModifyCustomer() {

        CustomerDto dto = new CustomerDto();
        dto.setName(NEW_CUSTOMER_NAME);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + MODIFY_CUSTOMER_ID, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        ResponseEntity<List<CustomerDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(9, response.getBody().size());

        CustomerDto customerSearch = response.getBody().stream().filter(item -> item.getId().equals(MODIFY_CUSTOMER_ID)).findFirst().orElse(null);
        assertNotNull(customerSearch);
        assertEquals(NEW_CUSTOMER_NAME, customerSearch.getName());
    }

    @Test
    public void modifyWithNotExistIdShouldInternalError() {

        CustomerDto dto = new CustomerDto();
        dto.setName(NEW_CUSTOMER_NAME);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + NEW_CUSTOMER_ID, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void deleteWithExistsIdShouldDeleteCustomer() {

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + DELETE_CUSTOMER_ID, HttpMethod.DELETE, null, Void.class);

        ResponseEntity<List<CustomerDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(8, response.getBody().size());
    }

    @Test
    public void deleteWithNotExistsIdShouldInternalError() {

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + NEW_CUSTOMER_ID, HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    @Test
    public void saveExistsCustomerNameShouldInternalError(){
       CustomerDto dto= new CustomerDto();
       dto.setName(EXISTS_CUSTOMER_NAME);
        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH , HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
