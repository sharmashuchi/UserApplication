package com.synchrony.userapp.repository;

import com.synchrony.userapp.domain.Image;
import com.synchrony.userapp.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, String> {

    List<Image> findByUser(User user);
    Image findByImageDeleteHash(String imageDeleteHash);
    String deleteByImageDeleteHash(String imageDeleteHash);
}
