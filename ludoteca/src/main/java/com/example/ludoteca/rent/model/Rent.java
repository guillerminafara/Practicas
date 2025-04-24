package com.example.ludoteca.rent.model;

import com.example.ludoteca.customer.model.Customer;
import com.example.ludoteca.game.model.Game;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Rent")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name="customer_name", nullable = false)
    private Long nameCustomer;

    @Column(name="game_name", nullable = false)
    private Long nameGame;

    @Column(name="initial_date", nullable = false)
    private Date initialDate;

    @Column(name="final_date", nullable = false)
    private Date finalDate;

    @Column(name="enable", nullable = false)
    private Boolean enable;
}
