package com.example.practica.web;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;


import java.util.Set;
import java.util.stream.Collectors;
@Service
public class PruebaServiceImpl implements PruebaService {
    @Autowired
    private Validator validator;

    @Override
    public String saludo(ObjetoEntrada entrada) {
        Errors errors = new BeanPropertyBindingResult(entrada, "user");
        Set<ConstraintViolation<ObjetoEntrada>> violations = validator.validate(entrada);

        if(violations.isEmpty())
            return "Hola "+ entrada.getNombre();
        return "errores"+ violations.stream().map(ConstraintViolation:: getMessage).collect(Collectors.joining(", "));
    }
}
