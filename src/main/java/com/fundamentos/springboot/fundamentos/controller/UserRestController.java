package com.fundamentos.springboot.fundamentos.controller;

import com.fundamentos.springboot.fundamentos.caseuse.*;
import com.fundamentos.springboot.fundamentos.configuration.CaseUseConfiguration;
import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    //create,get,delete,update
    private GetUser getUser;
    private CreateUser createUser;
    private DeleteUser deleteUser;

    private UpdateUser updateUser;
    private GetUserId getUserId;
    private GetUserPagiante getUserPagiante;

    public UserRestController(GetUser getUser, CreateUser createUser,DeleteUser deleteUser,
                              UpdateUser updateUser,GetUserId getUserId,GetUserPagiante getUserPagiante) {
        this.getUser = getUser;
        this.createUser=createUser;
        this.deleteUser=deleteUser;
        this.updateUser=updateUser;
        this.getUserId=getUserId;
        this.getUserPagiante=getUserPagiante;
    }

    @GetMapping
    List <User> getAllUser(){

        return  getUser.getAll();
    }

    @GetMapping("/{id}")

    User getUserId(@PathVariable Long id){

        return getUserId.getFindById(id);

    }

    @PostMapping
    ResponseEntity<User> newUser(@RequestBody User newUser){

        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id){

        deleteUser.remove(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    ResponseEntity<User> replaceUser(@PathVariable Long id,@RequestBody User userUpdate){
        return new ResponseEntity<>(updateUser.update(id,userUpdate), HttpStatus.OK);
    }

    @GetMapping("/pageable")
    List<User> getUserPageable(@RequestParam int page,@RequestParam int size){

        return getUserPagiante.getAllPaginate(page,size);

    }


}
