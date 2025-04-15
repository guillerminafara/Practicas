package com.example.practica.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/saludo")
public class PruebaController {

    @Autowired
    PruebaService pruebaService;
    @PostMapping(path = "/")
        public ResponseEntity<ObjetoSalida> saludo(@RequestBody ObjetoEntrada nombre){

        return new ResponseEntity(new ObjetoSalida(pruebaService.saludo(nombre)), HttpStatus.OK);
    }
//    public ResponseEntity<ObjetoSalida> saludo(@Valid @RequestBody ObjetoEntrada nombre, BindingResult bindingResult){
//
//        if(bindingResult.hasErrors()){
//            return new ResponseEntity<ObjetoSalida>(new ObjetoSalida("Con errores: "+ bindingResult.getAllErrors().toString()), HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity(new ObjetoSalida("Holaa"+ nombre), HttpStatus.OK);
//    }

}
