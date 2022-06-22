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

    public User register(User user) throws Exception {
        try{
            return repository.save(user);
        }catch (Exception e){
            throw new Exception("Error registering user: " + e.getMessage());
        }
    }

    public List<User> getUsers(){
        return (List<User>) repository.findAll();
    }
}
