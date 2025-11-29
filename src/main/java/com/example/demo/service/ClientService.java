package com.example.demo.service;

import com.example.demo.entity.ClientEntity;
import com.example.demo.exception.DeletionException;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final RentRepository rentRepository;

    public List<ClientEntity> getAllClients() {
        return clientRepository.findAll();
    }

    public ClientEntity getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public ClientEntity addClient(ClientEntity client) {
        return clientRepository.save(client);
    }

    public void delete(Long id) {
        if (clientRepository.existsById(id) && !rentRepository.existsByClientId(id) ) {
            clientRepository.deleteById(id);
        } else {
            throw new DeletionException("Клиент взял книгу");
        }
    }
}
