package com.example.demo.service;

import com.example.demo.dto.RentDto;
import com.example.demo.entity.BookEntity;
import com.example.demo.entity.ClientEntity;
import com.example.demo.entity.RentEntity;
import com.example.demo.exception.RentException;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentService {

    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;
    private final RentRepository rentRepository;

    public RentEntity takeBook(Long clientId, Long bookId) {

        ClientEntity client = clientRepository.getReferenceById(clientId);
        BookEntity book = bookRepository.getReferenceById(bookId);

        RentEntity rent = new RentEntity();
        rent.setClient(client);
        rent.setBook(book);
        rent.setRentDate(LocalDate.now());

        if (!rentRepository.existsByClientId(clientId)) {
            return rentRepository.save(rent);
        } else {
            throw new RentException("Пользователь уже взял книгу");
        }
    }

    public List<RentEntity> getAllRents() {
        return rentRepository.findAll();
    }

    public List<RentDto> getAllRentsJSON() {
       return getAllRents().stream()
                .map(r -> new RentDto(
                        r.getClient().getFullName(),
                        r.getClient().getBirthDate(),
                        r.getBook().getName(),
                        r.getBook().getAuthor(),
                        r.getBook().getIsbn(),
                        r.getRentDate()
                ))
                .collect(Collectors.toList());
    }

    public void delete(Long rentId) {
        rentRepository.deleteById(rentId);
    }
}
