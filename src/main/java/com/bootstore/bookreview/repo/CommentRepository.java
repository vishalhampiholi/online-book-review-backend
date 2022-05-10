package com.bootstore.bookreview.repo;

import com.bootstore.bookreview.model.Books;
import com.bootstore.bookreview.model.Comments;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comments,Long> {

    public List<Comments> findByBook(Books book);
}
