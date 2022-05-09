package com.bootstore.bookreview.repo;

import com.bootstore.bookreview.model.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users,Long> {
    public Users findByUsername(String username);
    public Users findByEmail(String email);
}
