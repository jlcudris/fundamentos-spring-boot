package com.fundamentos.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponetTwoimplements implements ComponentDependency {
    @Override
    public void saludar() {
        System.out.println("hola munde desade mi componente 2");
    }
}
