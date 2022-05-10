package com.bootstore.bookreview.service;

import com.bootstore.bookreview.model.Books;
import com.bootstore.bookreview.model.CommentReqDto;
import com.bootstore.bookreview.model.Comments;
import com.bootstore.bookreview.model.Users;
import com.bootstore.bookreview.repo.BookReopository;
import com.bootstore.bookreview.repo.CommentRepository;
import com.bootstore.bookreview.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    BookReopository bookReopository;

    @Autowired
    CommentRepository commentRepository;

    public Users saveUser(Users user){


        return userRepository.save(user);
    }
    public Users getUserByName(String name){
        return userRepository.findByUsername(name);
    }


    public void editUserProfile(Users user) {
        Users dbUser = userRepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException());
        dbUser.setEmail(user.getEmail());
        dbUser.setUsername(user.getUsername());
        dbUser.setRole(user.getRole());
        dbUser.setPassword(user.getPassword());
        userRepository.save(dbUser);
    }

    public void addComment(CommentReqDto comment) {
        Users user=userRepository.findById(comment.getUserId()).orElseThrow(()->new RuntimeException("User not found"));
        Books books=bookReopository.findById(comment.getBookId()).orElseThrow(()->new RuntimeException("book not found"));
        Comments comments=new Comments();
        comments.setBook(books);
        comments.setUser(user);
        comments.setCreatedTime(LocalDateTime.now());
        comments.setText(comment.getText());
        commentRepository.save(comments);
    }
}
