package com.bootstore.bookreview.repo;

import com.bootstore.bookreview.model.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Users,Long> {
    public Optional<Users> findById(Long id);
    public Users findByUsername(String username);
    public Users findByEmail(String email);
}
