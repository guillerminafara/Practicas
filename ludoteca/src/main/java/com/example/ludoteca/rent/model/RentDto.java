package com.example.ludoteca.rent.model;


import com.example.ludoteca.customer.model.Customer;
import com.example.ludoteca.game.model.Game;

import java.util.Date;

public class RentDto {
    private Long id;
    private Customer nameCustomer;
    private Game nameGame;
    private Date initialDate;
    private Date finalDate;
    private Boolean enable;
}
