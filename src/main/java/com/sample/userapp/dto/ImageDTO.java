package com.sample.userapp.dto;

public class ImageDTO {

    private String imageId;
    private String userId;
    private String imageDownloadLink;
    private String imageDeleteHash;

    public ImageDTO() {
    }

    public ImageDTO(String imageId, String userId, String imageDownloadLink, String imageDeleteHash) {
        this.imageId = imageId;
        this.userId = userId;
        this.imageDownloadLink = imageDownloadLink;
        this.imageDeleteHash = imageDeleteHash;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImageDownloadLink() {
        return imageDownloadLink;
    }

    public void setImageDownloadLink(String imageDownloadLink) {
        this.imageDownloadLink = imageDownloadLink;
    }

    public String getImageDeleteHash() {
        return imageDeleteHash;
    }

    public void setImageDeleteHash(String imageDeleteHash) {
        this.imageDeleteHash = imageDeleteHash;
    }
}
