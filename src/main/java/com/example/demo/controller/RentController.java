package com.example.demo.controller;

import com.example.demo.dto.RentDto;
import com.example.demo.service.BookService;
import com.example.demo.service.ClientService;
import com.example.demo.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rent")
public class RentController {

    private final RentService rentService;
    private final ClientService clientService;
    private final BookService bookService;

    @GetMapping
    public String form(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("rents", rentService.getAllRents()); // Добавил список аренд
        return "rent";
    }

    @PostMapping
    public String takeBook(@RequestParam Long clientId,
                           @RequestParam Long bookId) {
        rentService.takeBook(clientId, bookId);
        return "redirect:/rent"; // Возвращаем на эту же страницу
    }

    @PostMapping("/return")
    public String deleteRent(@RequestParam Long rentId) {
        rentService.delete(rentId);
        return "redirect:/rent"; // Возвращаем на эту же страницу
    }

    @GetMapping("/api")
    @ResponseBody
    public List<RentDto> getAllRentsJson() {
        return rentService.getAllRentsJSON();
    }
}
