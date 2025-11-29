package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RentDto {
    private String clientName;
    private LocalDate clientBirthday;
    private String bookName;
    private String author;
    private String isbn;
    private LocalDate takenAt;
}
