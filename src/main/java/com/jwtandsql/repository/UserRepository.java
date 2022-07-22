package com.jwtandsql.repository;

import com.jwtandsql.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
