package com.example.projectpfe.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Error {
    private  String field;
    private  String message;
}
