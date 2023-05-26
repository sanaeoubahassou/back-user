package com.example.projectpfe.exception;

public class ExceptionApi extends RuntimeException {
    private final Payload payload = new Payload();

    public ExceptionApi(PayLoadExceptionItem payload) {
        super(payload.getMessage());
        this.payload.setMessageError(payload.getMessage());
        this.payload.setResponseCode(payload.getStatusCode());
        this.payload.setDetail(payload.getDetail());
    }
    public Payload getPayload() {
        return payload;
    }

}