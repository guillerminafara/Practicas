package com.example.ludoteca.author.model;

import jakarta.validation.constraints.NotBlank;

public class AuthorDto {
    private Long id;
    @NotBlank(message= "No se admiten carácteres en blanco")
    private String name;
    @NotBlank(message= "No se admiten carácteres en blanco")
    private String nationality;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }


}
