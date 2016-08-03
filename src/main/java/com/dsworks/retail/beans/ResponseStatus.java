package com.dsworks.retail.beans;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseStatus {

    @JsonProperty
    private String code;

    @JsonProperty
    private String message;

    public String getCode() {
        return code;
    }

    public ResponseStatus setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseStatus setMessage(String message) {
        this.message = message;
        return this;
    }
}
