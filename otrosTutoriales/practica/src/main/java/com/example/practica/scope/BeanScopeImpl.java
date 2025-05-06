package com.example.practica.scope;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BeanScopeImpl implements BeanScope {
    private String mensaje;

    @Override
    public String getMensaje() {
        return mensaje;
    }

    @Override
    @Transactional
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
