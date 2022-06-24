package com.jroget.ncodingbackend.controllers;

import com.jroget.ncodingbackend.exceptions.NotFoundException;
import com.jroget.ncodingbackend.models.Response;
import com.jroget.ncodingbackend.models.User;
import com.jroget.ncodingbackend.services.AuthService;
import com.jroget.ncodingbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody User user){
        try{
            User registeredUser = userService.register(user);
            return this.sendResponseOk("User registered successfully", registeredUser);
        }catch (Exception e){
            return this.sendResponseServerError(e.getMessage(), user);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody User user){
        try{
            User userLogged = authService.login(user);
            return this.sendResponseOk("User logged successfully", userLogged);
        }catch (NotFoundException e) {
            return this.sendResponseNotFound(e.getMessage());
        }catch (Exception e){
            return this.sendResponseServerError(e.getMessage(), null);
        }
    }
}
