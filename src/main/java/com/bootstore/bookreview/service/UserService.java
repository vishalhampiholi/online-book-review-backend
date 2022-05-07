package com.bootstore.bookreview.service;

import com.bootstore.bookreview.model.Users;
import com.bootstore.bookreview.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users saveUser(Users user){
        return userRepository.save(user);
    }


}
