package com.fundamentos.springboot.fundamentos.bean;

public class MyBean2Implement implements MyBean {
    @Override
    public void print() {
        System.out.println("hola desde mi implementacion propia de bean 2");
    }
}
