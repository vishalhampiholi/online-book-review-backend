package com.bootstore.bookreview.security;

import com.bootstore.bookreview.model.Users;
import com.bootstore.bookreview.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user =userRepository.findByUsername(username);
        if(user==null) {
            throw new UsernameNotFoundException("Could not find User");
        }
        return new MyUserDetails(user);
    }

}

