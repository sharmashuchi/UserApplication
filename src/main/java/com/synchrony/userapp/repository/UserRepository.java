package com.synchrony.userapp.repository;

import com.synchrony.userapp.domain.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, String> {
}
