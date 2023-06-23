package org.example.controller;

import lombok.Setter;
import org.example.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
@Setter
public class ProfileController {
    @Autowired
    private Scanner scanner;
    @Autowired
    private ProfileService profileService;
    public void studentPage() {
        while (true){
            showStudentMenu();
            int selection = scanner.nextInt();
            switch (selection){
                case 1 -> profileService.getStudentList();
                case 2 -> profileService.searchStudent();
                case 3 -> profileService.blockingStudent();
                case 4 -> profileService.activateStudent();
                case 5 -> profileService.studentByBook();
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void showStudentMenu() {
        System.out.println("""
                *** Student ***
                1. Student list
                2. Search student
                3. Block student
                4. Activate student
                5. Student by book
                0. Exit
                """);

    }

    public void profilePage() {
        while (true){
            showProfileMenu();
            int selection = scanner.nextInt();
            switch (selection){
                case 1 -> profileService.getAllProfileList();
                case 2 -> profileService.searchProfile();
                case 3 -> profileService.changeStatus();
                case 0 -> {
                    return;
                }
                default -> System.out.println("❗️Wrong selection");
            }
        }
    }

    private void showProfileMenu() {
        System.out.println("""
                *** Profile *** (only for admin)
                1. Profile list
                2. Search profile
                3. Change profile status
                0. Exit
                """);
    }
}
