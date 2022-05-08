package com.bootstore.bookreview.controller;

import com.bootstore.bookreview.model.Books;
import com.bootstore.bookreview.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

   @Autowired
   private BookService bookService;


    @GetMapping("/getAllBooks")
    public List<Books> getAllBooks(){
       return  bookService.getAllBooks();
    }

   @PostMapping("/addBook")
   public  Books addBooks(@RequestBody Books book){
        return bookService.addBook(book);
   }

}
