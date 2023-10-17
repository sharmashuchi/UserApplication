package com.sample.userapp.repository;

import com.sample.userapp.domain.Image;
import com.sample.userapp.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, String> {

    List<Image> findByUser(User user);
    Image findByImageDeleteHash(String imageDeleteHash);
}
