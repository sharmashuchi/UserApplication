package com.synchrony.userapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataDTO {

    @JsonProperty("id")
    private String id;
    @JsonProperty("account_id")
    private String accountId;
    @JsonProperty("link")
    private String link;
    @JsonProperty("deletehash")
    private String deleteHash;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public DataDTO(String id, String accountId, String link) {
        this.id = id;
        this.accountId = accountId;
        this.link = link;
    }

    public String getDeleteHash() {
        return deleteHash;
    }

    public void setDeleteHash(String deleteHash) {
        this.deleteHash = deleteHash;
    }
}
