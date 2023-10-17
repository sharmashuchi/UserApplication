package com.sample.userapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImgurDeleteImageResponseDTO {

    @JsonProperty("status")
    private int status;
    @JsonProperty("success")
    private String success;
    @JsonProperty("data")
    private boolean data;

    public ImgurDeleteImageResponseDTO(int status, String success, Boolean data) {
        this.status = status;
        this.success = success;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Boolean getData() {
        return data;
    }

    public void setData(Boolean data) {
        this.data = data;
    }
}
