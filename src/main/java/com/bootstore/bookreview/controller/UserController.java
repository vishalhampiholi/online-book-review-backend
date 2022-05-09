package com.bootstore.bookreview.controller;

import com.bootstore.bookreview.model.CommentReqDto;
import com.bootstore.bookreview.model.Comments;
import com.bootstore.bookreview.model.Users;
import com.bootstore.bookreview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/signup")
    public Users signUp(@RequestBody Users user){
        String encodedpass=bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedpass);
        return userService.saveUser(user);
    }

    @GetMapping("/getUser/{name}")
    public Users getUser(@PathVariable String name){
        return userService.getUserByName(name);
    }



    @PostMapping("/editProfile")
    public void editProfile(@RequestBody Users user){
        userService.editUserProfile(user);
    }

    @PostMapping("/addComment")
    public ResponseEntity<?> addAUserCommentOnBook(@RequestBody CommentReqDto commentDto){
        userService.addComment(commentDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
