package com.fundamentos.springboot.fundamentos.caseuse;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetUserPagiante {

    private UserService userService;

    public GetUserPagiante(UserService userService) {
        this.userService = userService;
    }

    public List<User> getAllPaginate(int page, int size) {

        return userService.getAllUsersPaginate(page,size);
    }
}
