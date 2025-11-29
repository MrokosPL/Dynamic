package com.example.demo.controller;

import com.example.demo.entity.ClientEntity;
import com.example.demo.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "client";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("client", new ClientEntity());
        return "client_form";
    }

    @PostMapping("/add")
    public String add(ClientEntity client) {
        clientService.addClient(client);
        return "redirect:/client";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("client", clientService.getClientById(id));
        return "client_form";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, ClientEntity client) {
        client.setId(id);
        clientService.addClient(client);
        return "redirect:/client";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        clientService.delete(id);
        return "redirect:/client";
    }
}
