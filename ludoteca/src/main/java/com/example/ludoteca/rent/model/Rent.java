package com.example.ludoteca.rent.model;


import com.example.ludoteca.customer.model.Customer;
import com.example.ludoteca.game.model.Game;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "rent")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Column(name = "initial_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "final_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "enable", nullable = false)
    private Boolean enable;
}
