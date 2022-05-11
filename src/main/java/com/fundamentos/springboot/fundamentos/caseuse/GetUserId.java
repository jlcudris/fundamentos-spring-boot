package com.fundamentos.springboot.fundamentos.caseuse;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class GetUserId {

    private UserService userService;

    public GetUserId(UserService userService) {
        this.userService = userService;
    }

    public User getFindById(Long id) {
      return   userService.getFindByIDd(id);
    }
}

