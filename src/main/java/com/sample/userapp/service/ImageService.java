package com.sample.userapp.service;

import com.sample.userapp.domain.Image;
import com.sample.userapp.domain.User;
import com.sample.userapp.dto.ImageDTO;
import com.sample.userapp.dto.ImgurDeleteImageResponseDTO;
import com.sample.userapp.dto.ImgurResponseDTO;
import com.sample.userapp.properties.ImageProperties;
import com.sample.userapp.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class ImageService {

    private final Path imageLocation;

    private String uploadUri;

    private String deleteUri ;

    private String accessToken;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ImageRepository imageRepository;


    @Autowired
    public ImageService(ImageProperties imageProperties) throws Exception {
        this.imageLocation = Paths.get(imageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        this.uploadUri = imageProperties.getUploadUri();
        this.deleteUri = imageProperties.getDeleteUri();
        this.accessToken = imageProperties.getApiToken();

        System.out.println(imageLocation);
        System.out.println(uploadUri);
        System.out.println(deleteUri);
        System.out.println(accessToken);

        try {
            Files.createDirectories(this.imageLocation);
        } catch (Exception ex) {
            throw new Exception("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String uploadImage(MultipartFile image, User user) throws IOException {
        // Normalize file name
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());

        try {

            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new IOException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.imageLocation.resolve(fileName);
            Files.copy(image.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fsr=new FileSystemResource(new File(String.valueOf(targetLocation)));
            HttpHeaders headers = new HttpHeaders();
            //headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.setBearerAuth(this.accessToken);
            MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
            form.add("image", fsr);
            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(form,headers);

            ImgurResponseDTO response= restTemplate.exchange(
                    this.uploadUri, HttpMethod.POST, entity, ImgurResponseDTO.class).getBody();
            String imageId = response.getData().getId();
            String imageDeleteHash = response.getData().getDeleteHash();
            String imageLink = response.getData().getLink();
            Image imageToSave = new Image();
            imageToSave.setImageId(imageId);
            imageToSave.setUser(user);
            imageToSave.setImageDeleteHash(imageDeleteHash);
            imageToSave.setImageDownloadLink(imageLink);
            imageToSave.setUploadedOn(new Date());
            imageRepository.save(imageToSave);
            return imageId;
        } catch (Exception ex) {
            throw new IOException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public List<ImageDTO> getImageForUser(User user) {
        List<Image> imageList = imageRepository.findByUser(user);
        List<ImageDTO> imagesUploadedByUser = new ArrayList<ImageDTO>();
        if(!CollectionUtils.isEmpty(imageList)) {
            for (Image image: imageList) {
                ImageDTO imageUploaded = new ImageDTO(image.getImageId(), image.getUser().getUserId(), image.getImageDownloadLink(), image.getImageDeleteHash());
                imagesUploadedByUser.add(imageUploaded);
            }
        }

        return imagesUploadedByUser;
    }

    public String deleteImageForUser(String deleteHash) throws Exception {
        try {
            Image imageToDelete = imageRepository.findByImageDeleteHash(deleteHash);
            String imageId = imageToDelete.getImageId();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.setBearerAuth(this.accessToken);
            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(null,headers);

            ImgurDeleteImageResponseDTO response= restTemplate.exchange(this.deleteUri,
                    HttpMethod.DELETE,
                    entity,
                    ImgurDeleteImageResponseDTO.class,
                    deleteHash
            ).getBody();
            if(response.getStatus()==HttpStatus.OK.value()) {
                imageRepository.delete(imageToDelete);
                return imageId;
            } else {
                return "Image not deleted due to error"+response.getStatus();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to delete image", ex);
        }

    }
}
