package com.bootstore.bookreview.service;

import com.bootstore.bookreview.model.BookDetailDto;
import com.bootstore.bookreview.model.Books;
import com.bootstore.bookreview.model.Comments;
import com.bootstore.bookreview.repo.BookReopository;
import com.bootstore.bookreview.repo.CommentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class BookService {

   @Autowired
   private BookReopository bookReopository;

   @Autowired
   private CommentRepository commentRepository;

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
      dbbook.setId(book.getId());
      dbbook.setBook_name(book.getBook_name());
      dbbook.setAuthor(book.getAuthor());
      dbbook.setDescription(book.getDescription());
      dbbook.setPrice(book.getPrice());
      dbbook.setSeller_info(book.getSeller_info());
      dbbook.setActive(book.isActive());
     return bookReopository.save(dbbook);
    }

    public BookDetailDto getBookDetails(Long id){
      Books book=getBookByID(id);
      BookDetailDto bookDetailDto=new BookDetailDto();
        BeanUtils.copyProperties(bookDetailDto,book);
        bookDetailDto.setCommentsList(getCommentsOfBook(book));
      return  bookDetailDto;
    }

    public void setBookStatus(Long bookId,Boolean bookStatus) {
     Books book= bookReopository.findById(bookId).orElseThrow(()->new RuntimeException("Book didn't found while updating status"));
     book.setActive(bookStatus);
     bookReopository.save(book);
    }

    public void addLikes(Long bookId) {
      Books book=getBookByID(bookId);
      book.setLikes(book.getLikes()+1);
      bookReopository.save(book);
    }

    public List<Comments> getCommentsOfBook(Books book){
      return commentRepository.findByBook(book);
    }
}
