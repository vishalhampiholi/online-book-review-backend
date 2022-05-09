package com.bootstore.bookreview.repo;

import com.bootstore.bookreview.model.Books;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
@Repository
public interface BookReopository extends CrudRepository<Books,Long> {




}
