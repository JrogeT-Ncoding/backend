package com.jroget.ncodingbackend.services;

import com.jroget.ncodingbackend.models.User;
import com.jroget.ncodingbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User register(User user){
        return repository.save(user);
    }

    public List<User> getUsers(){
        return (List<User>) repository.findAll();
    }
}
