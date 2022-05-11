package com.fundamentos.springboot.fundamentos.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

//para quese construya el pojo a partir de las propiedades
@ConstructorBinding
//con esta anotacion le decimos que use el prefix para asocial el pojo con las propiedades
@ConfigurationProperties(prefix = "user")
public class UserPojo {
    private String email;
    private String password;
    private String age;

    public UserPojo(String email, String password, String age) {
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEdad() {
        return age;
    }

    public void setEdad(String edad) {
        this.age = edad;
    }

    @Override
    public String toString() {
        return email+" "+ " "+password +" "+ age;
    }
}
