package com.example.practica.web;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;

public class ObjetoEntrada {
private String nombre;

    @NotBlank
    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
