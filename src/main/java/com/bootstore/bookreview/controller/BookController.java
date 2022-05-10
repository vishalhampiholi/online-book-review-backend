package com.bootstore.bookreview.controller;

import com.bootstore.bookreview.model.BookDetailDto;
import com.bootstore.bookreview.model.Books;
import com.bootstore.bookreview.model.Comments;
import com.bootstore.bookreview.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> deleteBook(@PathVariable Long id ){
        bookService.deleteBook(id);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/getBookById/{id}")
    public Books getBookById(@PathVariable Long id){
        return  bookService.getBookByID(id);
    }

    @GetMapping("/getBookDetails/{id}")
    public  BookDetailDto getBookDetails(@PathVariable Long id){
       return bookService.getBookDetails(id);
    }

    @PostMapping("/bookStatus/{bookId}")
    public ResponseEntity<?> setBookStatus(@PathVariable Long bookId, @RequestParam Boolean bookStatus){
        bookService.setBookStatus( bookId, bookStatus);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/bookLike/{bookId}")
    public ResponseEntity<?> addLikes(@PathVariable Long bookId){
        bookService.addLikes(bookId);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }


}
