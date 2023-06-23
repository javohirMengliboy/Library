package org.example.controller;

import lombok.Setter;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
@Setter
public class CategoryController {
    @Autowired
    private Scanner scanner;
    @Autowired
    private CategoryService categoryService;
    public void categoryPage() {
        while (true){
            showCategoryMenu();
            int selection = scanner.nextInt();
            switch (selection){
                case 1 -> categoryService.getCategoryList();
                case 2 -> categoryService.addCategory();
                case 0 -> {
                    return;
                }
                default -> System.out.println("❗️Wrong selection");
            }
        }
    }

    private void showCategoryMenu() {

        System.out.println("""
                *** Category ***
                1. Category list
                2. Delete category
                0. Exit
                """);
    }
}
