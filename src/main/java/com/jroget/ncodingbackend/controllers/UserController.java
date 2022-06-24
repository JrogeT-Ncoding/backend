package com.jroget.ncodingbackend.controllers;

import com.jroget.ncodingbackend.models.Response;
import com.jroget.ncodingbackend.models.User;
import com.jroget.ncodingbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

    @Autowired
    private UserService service;

    @GetMapping()
    public ResponseEntity<Response> getAll(){
        try{
            List<User> users = service.getUsers();
            return this.sendResponseOk("Users retrieved successfully", users);
        }catch (Exception e){
            return this.sendResponseServerError("Error retrieving users: " + e.getMessage(),null);
        }
    }
}
