package org.example.controller;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
@Setter
public class AdminController {
    @Autowired
    private Scanner scanner;
    @Autowired
    private BookController bookController;
    @Autowired
    private CategoryController categoryController;
    @Autowired
    private ProfileController profileController;
    public void start(){
        while (true){
            showAdminMenu();
            int selection = scanner.nextInt();
            switch (selection){
                case 1 -> bookController.bookPage();
                case 2 -> categoryController.categoryPage();
                case 3 -> profileController.studentPage();
                case 4 -> profileController.profilePage();
                case 0 -> {
                    return;
                }
                default -> {
                    System.out.println("❗️Wrong selection");
                }
            }
        }
    }

    private void showAdminMenu() {
        System.out.println("""
                *** Menu ***
                1. Book
                2. Category  (only for admin)
                3. Student
                4. Profile (only for admin)
                0. Exit
               """);
    }
}
