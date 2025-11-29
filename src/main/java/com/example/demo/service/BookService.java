package com.example.demo.service;

import com.example.demo.entity.BookEntity;
import com.example.demo.exception.DeletionException;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final RentRepository rentRepository;

    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    public BookEntity getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public BookEntity addBook(BookEntity book) {
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        if (bookRepository.existsById(id) && !rentRepository.existsByBookId(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new DeletionException("Книга выдана клиенту");
        }

    }
}
