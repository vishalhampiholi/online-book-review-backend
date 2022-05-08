package com.bootstore.bookreview.service;

import com.bootstore.bookreview.model.Books;
import com.bootstore.bookreview.repo.BookReopository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
