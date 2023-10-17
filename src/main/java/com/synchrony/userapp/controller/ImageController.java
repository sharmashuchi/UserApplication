package com.synchrony.userapp.controller;


import com.synchrony.userapp.domain.User;
import com.synchrony.userapp.dto.ImageDTO;
import com.synchrony.userapp.dto.ResponseDTO;
import com.synchrony.userapp.service.ImageService;
import com.synchrony.userapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageService imageService;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDTO> uploadImage(@RequestParam MultipartFile image,@RequestParam String userId, @RequestParam String password) throws IOException {
        User user = userService.getUserDetails(userId);
        ResponseDTO response = new ResponseDTO();
        //TODO: validate user id and password and throw error
        if(user == null || !user.getPassword().equalsIgnoreCase(password)) {
            //TODO: remove hard-coding
            response.setResponseMessage("Incorrect credentials");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        String id = imageService.uploadImage(image, user);
        response.setResponseMessage(id);
        response.setUserId(userId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getImageListForTheUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String userId = userDetails.getUsername();
        ResponseDTO response = new ResponseDTO();
        List<ImageDTO> images = imageService.getImageForUser(userService.getUserDetails(userId));
        response.setResponseMessage("Images fetched successfully");
        response.setImages(images);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDTO> deleteSelectedImageByHash(@RequestParam String deleteHash) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String userId = userDetails.getUsername();
        ResponseDTO response = new ResponseDTO();
        try {
            String imageId = imageService.deleteImageForUser(deleteHash);
            response.setResponseMessage("Image with id" + imageId+" deleted successfully");
            response.setUserId(userId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setResponseMessage(ex.getMessage());
            response.setUserId(userId);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
