package com.example.projectpfe.exception;

public enum PayLoadExceptionItem {
    SYSTEM_ERROR("API SYSTEM ERROR", 100),
    ERROR("", 100),
    INVALID_INPUT("INVALID INPUT", 101),

    User_Not_Found("user not found", 103),

    Igg_Already_Used("ce igg deja utiliser", 104),
    Auth_Error("authentication erreur try again", 105),
    Password_Is_Required("password is required", 106),
    Email_Is_Required("email is required", 107),
    Email_And_Password_Are_Required("email and passsword are required", 108),
    User_Role_Not_Found("user role not found", 109),
    HTTP_CALL_ERROR("Http call error", 110),
    Email_Already_Used("ce email deja utiliser", 111),



    ;
    private String message;
    private String detail;
    private Integer statusCode;

    PayLoadExceptionItem(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public PayLoadExceptionItem detail(String d) {
        this.detail = d;
        return this;
    }

    public PayLoadExceptionItem message(String m) {
        this.message = m;
        return this;
    }

    public String getDetail() {
        return detail;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    @Override
    public String toString() {
        return (
                "PayLoadExceptionItem{" +
                        "message='" +
                        message +
                        '\'' +
                        ", detail='" +
                        detail +
                        '\'' +
                        ", statusCode=" +
                        statusCode +
                        '}'
        );
    }
}