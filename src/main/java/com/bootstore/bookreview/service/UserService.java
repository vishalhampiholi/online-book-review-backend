package com.bootstore.bookreview.service;

import com.bootstore.bookreview.model.Users;
import com.bootstore.bookreview.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public Users saveUser(Users user){


        return userRepository.save(user);
    }
    public Users getUserByName(String name){
        return userRepository.findByUsername(name);
    }


}
