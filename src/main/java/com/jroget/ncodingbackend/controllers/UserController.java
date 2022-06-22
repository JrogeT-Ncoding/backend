package com.jroget.ncodingbackend.controllers;

import com.jroget.ncodingbackend.models.User;
import com.jroget.ncodingbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    /*public UserController(UserService service){
        this.service = service;
    }*/

    @PostMapping
    public @ResponseBody User register(@RequestBody User user){
        return service.register(user);
    }

    @GetMapping
    public @ResponseBody List<User> listAll(){
        return service.getUsers();
    }
}
