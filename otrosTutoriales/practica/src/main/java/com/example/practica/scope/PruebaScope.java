package com.example.practica.scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PruebaScope implements Prueba{
    @Autowired
    BeanScope prueba1;

    @Autowired
    BeanScope prueba2;

    public void execute(){
        prueba1.setMensaje("Mensaje 1 ");
        prueba2.setMensaje("Mensaje 2 ");

        System.out.println(prueba1.getMensaje());
        System.out.println(prueba2.getMensaje());

    }
}
