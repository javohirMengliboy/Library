package org.example.controller;

import lombok.Setter;
import org.example.service.BookService;
import org.example.service.TakenBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
@Setter
public class BookController {
    @Autowired
    private Scanner scanner;
    @Autowired
    private BookService bookService;
    @Autowired
    private TakenBookService takenBookService;
    public void bookPage() {
        while (true){
            showBookMenu();
            int selection = scanner.nextInt();
            switch (selection){
                case 1 -> bookService.getBookList();
                case 2 -> bookService.searchBook();
                case 3 -> bookService.addBook();
                case 4 -> bookService.removeBook();
                case 5 -> takenBookService.getTakenBooks();
                case 6 -> takenBookService.getBookHistory();
                case 7 -> takenBookService.getBestBookList();
                case 0 -> {
                    return;
                }
                default -> {
                    System.out.println("Wrong selection");
                }
            }
        }
    }

    private void showBookMenu() {
        System.out.println("""
                *** Book ***
                1. Book list
                2. Search
                3. Add book
                4. Remove book
                5. Books on hand
                6. Book history
                7. Best books
                0. Exit
                """);
    }
}
