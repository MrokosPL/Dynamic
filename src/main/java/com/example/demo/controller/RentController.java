package com.example.demo.controller;

import com.example.demo.dto.RentDto;
import com.example.demo.service.BookService;
import com.example.demo.service.ClientService;
import com.example.demo.service.RentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Контроллер для взаимодействия с выдачей книг")
@Controller
@RequiredArgsConstructor
@RequestMapping("/rent")
public class RentController {

    private final RentService rentService;
    private final ClientService clientService;
    private final BookService bookService;

    @Operation(summary = "Метод для вызова формы выдачи книги")
    @GetMapping
    public String form(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("rents", rentService.getAllRents()); // Добавил список аренд
        return "rent";
    }

    @Operation(summary = "Метод для выдачи книги клиенту")
    @PostMapping
    public String takeBook(@RequestParam Long clientId,
                           @RequestParam Long bookId) {
        rentService.takeBook(clientId, bookId);
        return "redirect:/rent"; // Возвращаем на эту же страницу
    }

    @Operation(summary = "Метод для удаления записи выдачи книги")
    @PostMapping("/return")
    public String deleteRent(@RequestParam Long rentId) {
        rentService.delete(rentId);
        return "redirect:/rent"; // Возвращаем на эту же страницу
    }

}
