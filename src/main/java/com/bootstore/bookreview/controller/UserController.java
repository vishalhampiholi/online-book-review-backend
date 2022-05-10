package com.bootstore.bookreview.controller;

import com.bootstore.bookreview.model.Users;
import com.bootstore.bookreview.repo.UserRepository;
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

    @Autowired
    private UserRepository userRepository;


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


    @PostMapping("/changePassword")
    public ResponseEntity<Users> changePassword(@RequestParam("oldPassword") String oldPass, @RequestParam("newPassword") String newPass, @RequestParam("username") String username){

        Users currentUser=userRepository.findByUsername(username);
        if(bCryptPasswordEncoder.matches(oldPass,currentUser.getPassword())){
            currentUser.setPassword(bCryptPasswordEncoder.encode(newPass));
            this.userRepository.save(currentUser);
            return new  ResponseEntity<Users>(HttpStatus.OK);
        }else{
            return new ResponseEntity<Users>(HttpStatus.BAD_REQUEST);
        }



    }

@PostMapping("/udpateUser")
    public Users updateUsers(@RequestParam("oldName") String oldusername,@RequestParam("newName") String newname,@RequestParam("email") String email){
        Users curr=userRepository.findByUsername(oldusername);
        curr.setUsername(newname);
        curr.setEmail(email);
        userRepository.save(curr);
    return curr;
    }
}
