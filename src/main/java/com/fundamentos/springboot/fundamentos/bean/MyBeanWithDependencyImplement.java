package com.fundamentos.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency {

    private Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);

  private   MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        LOGGER.info("hemos ingresado al metodo printWithDependency");
        int numero =1;
        LOGGER.debug("el numero enviado como parametro a la  dependencia Operation es: "+numero);
        System.out.println(myOperation.suma(numero));
        System.out.println("Hola desde la implementacion desde el Bean con dependencia");
    }
}
