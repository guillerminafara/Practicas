package com.example.ludoteca.rent.model;

import com.example.ludoteca.customer.model.CustomerDto;
import com.example.ludoteca.game.model.GameDto;


import java.time.LocalDate;
import java.util.Date;

public class RentDto {
    private Long id;
    private CustomerDto customer;
    private GameDto game;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean enable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public GameDto getGame() {
        return game;
    }

    public void setGame(GameDto game) {
        this.game = game;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
