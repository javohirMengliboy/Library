package org.example.controller;

import lombok.Setter;
import org.example.service.BookService;
import org.example.service.TakenBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
@Setter
public class StudentController {

    @Autowired
    private Scanner scanner;

    @Autowired
    private BookService bookService;
    @Autowired
    private TakenBookService takenBookService;

    public void studentPage() {
        while (true){
            showStudentMenu();
            int selection = scanner.nextInt();
            switch (selection){
                case 1 -> bookService.getBookList();
                case 2 -> bookService.searchBook();
                case 3 -> takenBookService.takeBook();
                case 4 -> takenBookService.returnBook();
                case 5 -> takenBookService.booksOnHand();
                case 6 -> takenBookService.takeBookHistory();
                case 0 -> {
                    return;
                }
                default -> System.out.println("❗️Wrong selection");
            }

        }
    }

    private void showStudentMenu(){
        System.out.println("""
                *** Student Menu ***
                1. Book list
                2. Search
                3. Take book
                4. Return book
                5. Books on hand
                6. Take book history
                0. Exit
                
                Choose action
                """);
    }
}
