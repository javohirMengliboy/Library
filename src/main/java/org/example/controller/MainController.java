package org.example.controller;

import lombok.Setter;
import org.example.service.BookService;
import org.example.service.ProfileService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
@Setter
public class MainController {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private Scanner scanner;
    @Autowired
    private BookService bookService;

    @Autowired
    private ProfileService profileService;

    public void start(){
        mainPage();
    }

    public void showMainMenu(){
        System.out.println("""
                1. BookList
                2. Search
                3. By category
                4. Login
                5. Registration
                0. Exit
                
                Choose!
                """);
    }

    public void mainPage(){
        while (true){
            showMainMenu();
            int selection = scanner.nextInt();
            switch (selection){
                case 1 -> bookService.getBookList();
                case 2 -> bookService.searchBook();
                case 3 -> bookService.getBookListByCategory();
                case 4 -> profileService.login();
                case 5 -> profileService.registration();
                case 0 -> {
                    return;
                }
                default -> System.out.println("❗️Wrong selection");
            }
        }
    }
}
