package com.sample.userapp.repository;

import com.sample.userapp.domain.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, String> {
}
