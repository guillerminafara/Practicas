package com.example.ludoteca.customer.model;

import jakarta.validation.constraints.NotBlank;

public class CustomerDto {
    private Long id;
    @NotBlank(message= "No se admiten carácteres en blanco")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
