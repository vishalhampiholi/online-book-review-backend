package com.bootstore.bookreview.repo;

import com.bootstore.bookreview.model.Comments;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comments,Long> {
}
