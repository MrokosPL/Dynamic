package com.example.demo.controller;

import com.example.demo.entity.ClientEntity;
import com.example.demo.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Tag(name = "Контроллер для взаимодействия с клиентами")
@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @Operation(summary = "Метод для вызова списка всех клиентов")
    @GetMapping
    public String list(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "client";
    }

    @Operation(summary = "Метод для вызова формы добавления клиента")
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("client", new ClientEntity());
        return "client_form";
    }

    @Operation(summary = "Метод для добавления клиента")
    @PostMapping("/add")
    public String add(ClientEntity client) {
        clientService.addClient(client);
        return "redirect:/client";
    }

    @Operation(summary = "Метод для вызова формы редактирования клиента")
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("client", clientService.getClientById(id));
        return "client_form";
    }

    @Operation(summary = "Метод для редактирования данных клиента")
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, ClientEntity client) {
        client.setId(id);
        clientService.addClient(client);
        return "redirect:/client";
    }

    @Operation(summary = "Метод для удаления клиента")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        clientService.delete(id);
        return "redirect:/client";
    }
}
