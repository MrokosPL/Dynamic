package com.example.demo.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("status", "500");
        return "error";
    }

    @ExceptionHandler(DeletionException.class)
    public String handleNotFound(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("status", "400");
        return "error";
    }

    @ExceptionHandler(RentException.class)
    public String handleRentException(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("status", "409");
        return "error";
    }
}
