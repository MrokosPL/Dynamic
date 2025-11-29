package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DeletionException extends ResponseStatusException {

    public DeletionException(String response) {
        super(HttpStatus.BAD_REQUEST, response);
    }
}
