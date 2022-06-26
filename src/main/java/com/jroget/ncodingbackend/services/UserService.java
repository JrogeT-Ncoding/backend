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
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User register(User user) throws Exception {
        try{
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }catch (Exception e){
            throw new Exception("Error registering user: " + e.getMessage());
        }
    }

    public List<User> getUsers(){
        return (List<User>) userRepository.findAll();
    }

    public User update(int id, User user) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        User updatedUser = optionalUser.get();
        updatedUser.setEmail(user.getEmail());
        updatedUser.setFullName(user.getFullName());
        updatedUser.setCountry(user.getCountry());
        updatedUser.setPhone(user.getPhone());
        updatedUser.setAddress(user.getAddress());
        updatedUser.setEducationLevel(user.getEducationLevel());
        updatedUser.setBirthDate(user.getBirthDate());
        updatedUser.setGender(user.getGender());
        updatedUser.setPreferredLanguage(user.getPreferredLanguage());
        return userRepository.save(updatedUser);
    }
}
