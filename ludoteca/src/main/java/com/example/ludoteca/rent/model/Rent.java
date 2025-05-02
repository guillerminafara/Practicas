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
    private LocalDate intialDate;

    @Column(name = "final_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "enable", nullable = false)
    private Boolean enable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public LocalDate getIntialDate() {
        return intialDate;
    }

    public void setIntialDate(LocalDate intialDate) {
        this.intialDate = intialDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
