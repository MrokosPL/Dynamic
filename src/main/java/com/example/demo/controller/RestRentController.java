package com.example.demo.controller;

import com.example.demo.dto.RentDto;
import com.example.demo.service.RentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rent/api")
public class RestRentController {

    private final RentService rentService;

    @Operation(summary = "Метод для вызова списка всех активных выдач в виде JSON")
    @GetMapping
    @ResponseBody
    public List<RentDto> getAllRentsJson() {
        return rentService.getAllRentsJSON();
    }
}
