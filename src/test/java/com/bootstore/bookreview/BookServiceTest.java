package com.bootstore.bookreview;

import com.bootstore.bookreview.model.Books;
import com.bootstore.bookreview.repo.BookReopository;
import com.bootstore.bookreview.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTest {

    @Mock
   private BookReopository bookReopository;

    @InjectMocks
    private BookService bookService = new BookService();

/*    @BeforeEach
    public void beforeEach(){
        //Optional<User> emptyOptional=Optional.empty();
        Books book= new Books();
//        List<Books> booksList=new ArrayList<>();
//        Books harryPotterPart1=new Books();
//        harryPotterPart1.setActive(true);
//        harryPotterPart1.setAuthor("J K Rowling");
//        booksList.add(harryPotterPart1);
        when(bookReopository.findById(21l)).thenReturn(Optional.of(book));
   //     when(bookReopository.findAll()).thenReturn(booksList);

    }*/

    @Test
    public void editBookTest(){
       Books book1=new Books();
       book1.setId(21l);
       book1.setBook_name("Focus");

        when(bookReopository.findById(book1.getId())).thenReturn(Optional.of(book1));
        when(bookReopository.save(book1)).thenReturn(book1);
        Assertions.assertNotNull(bookService.editBook(book1));
    }
}
