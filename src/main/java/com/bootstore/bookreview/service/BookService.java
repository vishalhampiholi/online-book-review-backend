package com.bootstore.bookreview.service;

import com.bootstore.bookreview.model.Books;
import com.bootstore.bookreview.repo.BookReopository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class BookService {

   @Autowired
   private BookReopository bookReopository;

  public List<Books>  getAllBooks(){
      return (List<Books>) bookReopository.findAll();
   }

   public Books addBook(Books book){
     return bookReopository.save(book);
   }

    public void deleteBook(Long id) {
      bookReopository.deleteById(id);
    }


    public Books getBookByID(Long id) {
     return  bookReopository.findById(id).orElseThrow(()->new IllegalArgumentException());
    }

    public Books editBook(Books book) {
      Books dbbook=getBookByID(book.getId());
      dbbook.setBook_name(book.getBook_name());
      dbbook.setAuthor(book.getAuthor());
      dbbook.setDescription(book.getDescription());
      dbbook.setPrice(book.getPrice());
      dbbook.setSeller_info(book.getSeller_info());
     return bookReopository.save(dbbook);
    }
}
