package com.jroget.ncodingbackend.services;

import com.jroget.ncodingbackend.exceptions.NotFoundException;
import com.jroget.ncodingbackend.models.User;
import com.jroget.ncodingbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public User login(User user) throws Exception {
        try{
            Optional<User> userFound = userRepository.findByEmail(user.getEmail());
            if(userFound.isEmpty()){
                throw new NotFoundException("User not found");
            }
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if(!encoder.matches(user.getPassword(), userFound.get().getPassword())){
                throw new Exception("Invalid password");
            }
            return userFound.get();
        }catch (NotFoundException e){
            throw new NotFoundException("Error on user login: User not found");
        }catch (Exception e){
            throw new Exception("Error on user login: " + e.getMessage());
        }
    }
}
