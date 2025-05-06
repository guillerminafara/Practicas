package com.example.practica;


import com.example.practica.scope.Prueba;
import com.example.practica.scope.PruebaScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticaApplication implements CommandLineRunner {
    @Autowired
//    PruebaScope prueba;
    @Qualifier("pruebaScope")
    Prueba prueba;


    public static void main(String[] args) {

        SpringApplication.run(PracticaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        prueba.execute();
    }
}
