package com.fundamentos.springboot.fundamentos.bean;

public class MyBeanWithProperitesImplement implements MyBeanWithProperites{
    private String nombre;
    private String apellido;

    public MyBeanWithProperitesImplement(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public String function() {
        return nombre + '-' + apellido;
    }
}
