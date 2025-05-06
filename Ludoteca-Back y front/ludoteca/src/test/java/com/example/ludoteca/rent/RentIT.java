package com.example.ludoteca.rent;

import com.example.ludoteca.common.pagination.PageableRequest;
import com.example.ludoteca.config.ResponsePage;
import com.example.ludoteca.game.model.GameDto;
import com.example.ludoteca.rent.model.RentDto;
import com.example.ludoteca.rent.model.RentSearchDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class RentIT {
    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/rent";
    private static final String TITLE_PARAM = "name";
    private static final String RENT_ID = "idRent";
    public static final int RENTS_WITHOUT_FILTER = 6;
    private static final int TOTAL_RENTS = 6;
    private static final int PAGE_SIZE = 5;
    public static final Long DELETE_RENT_ID = 6L;
    public static final Long CUSTOMER_WITHOUT_RENTS=5L;
    private static final Long GAME_WITHOUT_RENTS = 4L;
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    ParameterizedTypeReference<ResponsePage<RentDto>>responseTypePage= new ParameterizedTypeReference<ResponsePage<RentDto>>() {
    };






    @Test
    public void findFirstPageWithFiveSizeShouldReturnFirstFiveResults() {
        RentSearchDto searchDto = new RentSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));

        ResponseEntity<ResponsePage<RentDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_RENTS, response.getBody().getTotalElements());
        assertEquals(PAGE_SIZE, response.getBody().getContent().size());
    }

    @Test
    public void findSecondPageWithFiveSizeShouldReturnLastResult() {

        int elementsCount = TOTAL_RENTS - PAGE_SIZE;

        RentSearchDto searchDto = new RentSearchDto();
        searchDto.setPageable(new PageableRequest(1, PAGE_SIZE));

        ResponseEntity<ResponsePage<RentDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_RENTS, response.getBody().getTotalElements());
        assertEquals(elementsCount, response.getBody().getContent().size());
    }
    @Test
    public void deleteWithExistsIdShouldDeleteCategory() {

        long newRentsSize = TOTAL_RENTS - 1;

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + DELETE_RENT_ID, HttpMethod.DELETE, null, Void.class);

        RentSearchDto searchDto = new RentSearchDto();
        searchDto.setPageable(new PageableRequest(0, TOTAL_RENTS));

        ResponseEntity<ResponsePage<RentDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(newRentsSize, response.getBody().getTotalElements());
    }

    private String getUrlWithParams() {
        return UriComponentsBuilder.fromHttpUrl(LOCALHOST + port + SERVICE_PATH)
                .queryParam(TITLE_PARAM, "{" + TITLE_PARAM + "}")
                .queryParam(RENT_ID, "{" + RENT_ID + "}")
                .encode()
                .toUriString();
    }

    @Test
    public void findWithoutFiltersShouldReturnAllRentInDB() {
        RentSearchDto searchDto = new RentSearchDto();
        searchDto.setPageable(new PageableRequest(1, PAGE_SIZE));

        ResponseEntity<ResponsePage<RentDto>> response = restTemplate.exchange(LOCALHOST+ port+ SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);
        assertNotNull(response);
        assertEquals(RENTS_WITHOUT_FILTER, response.getBody().getTotalElements());

    }

    @Test
    public void findNotExistCustomerShouldReturnEmpty() {

        RentSearchDto searchDto = new RentSearchDto();
        searchDto.setPageable(new PageableRequest(1, PAGE_SIZE));

        ResponseEntity<ResponsePage<RentDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "?customerId=" + CUSTOMER_WITHOUT_RENTS, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(0, response.getBody().getTotalElements());
    }

    @Test
    public void findNotExistGameShouldReturnEmpty() {

        RentSearchDto searchDto = new RentSearchDto();
        searchDto.setPageable(new PageableRequest(1, PAGE_SIZE));

        ResponseEntity<ResponsePage<RentDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "?gameId=" + GAME_WITHOUT_RENTS, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(0, response.getBody().getTotalElements());
    }

}
