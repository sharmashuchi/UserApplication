package com.synchrony.userapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImgurResponseDTO {

    @JsonProperty("status")
    private String status;
    @JsonProperty("success")
    private String success;
    @JsonProperty("data")
    private DataDTO data;

    public ImgurResponseDTO(String status, String success, DataDTO data) {
        this.status = status;
        this.success = success;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }
}
