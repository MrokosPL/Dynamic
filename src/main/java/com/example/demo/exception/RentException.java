package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RentException extends ResponseStatusException {

    public RentException(String message) {
        super(HttpStatus.CONFLICT ,message);
    }
}
