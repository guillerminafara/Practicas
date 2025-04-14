package com.example.practica.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/saludo")
public class PruebaController {

    @GetMapping(path = "/")
    public ResponseEntity<ObjetoSalida> saludo(String nombre){
        return new ResponseEntity(new ObjetoSalida("Holaa"+ nombre), HttpStatus.OK);
    }

}
