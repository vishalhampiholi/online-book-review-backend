package com.bootstore.bookreview.controller;

import com.bootstore.bookreview.model.Books;
import com.bootstore.bookreview.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
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

    @PutMapping("/editBook")
    public Books editBooksDetails(@RequestBody Books book){
        return bookService.editBook(book);
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseBody deleteBook(@PathVariable Long id ){
        bookService.deleteBook(id);
        return  null;
    }

    @GetMapping("/getBookById/{id}")
    public Books getBookById(@PathVariable Long id){
        return  bookService.getBookByID(id);
    }

}
