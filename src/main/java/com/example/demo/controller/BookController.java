package com.example.demo.controller;

import com.example.demo.entity.BookEntity;
import com.example.demo.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Tag(name = "Контроллер для взаимодействия с книгами")
@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Operation(summary = "Метод для получения списка книг")
    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books";
    }

    @Operation(summary = "Метод вызова формы добавить")
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("book", new BookEntity());
        return "book_form";
    }

    @Operation(summary = "Метод для добавления книги")
    @PostMapping("/add")
    public String addBook(BookEntity book) {
        bookService.addBook(book);
        return "redirect:/books";
    }

    @Operation(summary = "Метод вызова формы редактировать")
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "book_form";
    }

    @Operation(summary = "Метод для изменения данных книги")
    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, BookEntity book) {
        book.setId(id);
        bookService.addBook(book);
        return "redirect:/books";
    }

    @Operation(summary = "Метод для удаления книги")
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
