package com.bootstore.bookreview.security;

import com.bootstore.bookreview.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public  class MyUserDetails implements UserDetails {

   private Users user;

   public MyUserDetails(Users users){
      this.user=users;

   }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return Collections.singleton(new SimpleGrantedAuthority(user.getRole()));

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
