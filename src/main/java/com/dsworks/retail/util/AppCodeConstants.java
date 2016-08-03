package com.dsworks.retail.util;


public enum AppCodeConstants {

    SUCCESS("2000", "Success"),

    RESOURCE_NOT_FOUND("4040", "No such Product exists with this ID."),

    DATABASE_ERROR("3000", "Database Error"),

    UNEXPECTED_ERROR("5001", "Unexpected Error"),

    INPUT_VALIDATION_FAILED("4001", "Input Validation Failed. Please try with valid ID.");

    private String code;

    private String message;

    AppCodeConstants(final String code, final String message) {
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
