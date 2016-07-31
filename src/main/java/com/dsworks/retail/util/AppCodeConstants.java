package com.dsworks.retail.util;


public enum AppCodeConstants {

    RESOURCE_NOT_FOUND("2001", "Resource Not Found. Please try with valid Product ID"),

    DATABASE_ERROR ("3000", "Database Error"),

    UNEXPECTED_ERROR("5001","Unexpected Error");

    private String code;

    private String message;

    AppCodeConstants (final String code, final String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
