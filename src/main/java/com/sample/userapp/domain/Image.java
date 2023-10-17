package com.sample.userapp.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @Column(name="IMAGE_ID")
    private String imageId;

   @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name="USER_ID", referencedColumnName = "USER_ID", nullable = false)
    private User user;

   @Column(name="IMAGE_DELETE_HASH")
   private String imageDeleteHash;

   @Column(name="IMAGE_DOWNLOAD_LINK")
   private String imageDownloadLink;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name="UPLOADED_ON")
    private Date uploadedOn;

    public Image() {
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Date getUploadedOn() {
        return uploadedOn;
    }

    public void setUploadedOn(Date uploadedOn) {
        this.uploadedOn = uploadedOn;
    }

    public String getImageDeleteHash() {
        return imageDeleteHash;
    }

    public void setImageDeleteHash(String imageDeleteHash) {
        this.imageDeleteHash = imageDeleteHash;
    }

    public String getImageDownloadLink() {
        return imageDownloadLink;
    }

    public void setImageDownloadLink(String imageDownloadLink) {
        this.imageDownloadLink = imageDownloadLink;
    }
}
