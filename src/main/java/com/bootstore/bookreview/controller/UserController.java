package com.bootstore.bookreview.controller;

import com.bootstore.bookreview.model.Users;
import com.bootstore.bookreview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/signup")
    public Users signUp(@RequestBody Users user){
        return userService.saveUser(user);
    }
}
