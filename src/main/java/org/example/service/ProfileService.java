package org.example.service;

import lombok.Setter;
import org.example.container.ComponentContainer;
import org.example.controller.AdminController;
import org.example.controller.StudentController;
import org.example.dto.ProfileDTO;
import org.example.entity.ProfileEntity;
import org.example.enums.ProfileRole;
import org.example.enums.ProfileStatus;
import org.example.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Service
@Setter
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private Scanner scanner;
    @Autowired
    private StudentController studentController;
    @Autowired
    private AdminController adminController;

    public void login() {
        System.out.print("Enter login ");
        String login = scanner.next();

        ProfileDTO profile = profileRepository.getProfileByLogin2(login);
        if (profile == null){
            System.out.println("Login not found");
            return;
        }

        System.out.println("Enter password");
        String password = scanner.next();

        if (!password.equals(profile.getPassword())){
            System.out.println("Wrong password");
            return;
        }

        ComponentContainer.profileDTO = profile;
        if (profile.getRole().equals(ProfileRole.STUDENT)){
            studentController.studentPage();
        }else {
            adminController.start();
        }
    }

    public void registration() {
        String name;
        do {
            System.out.print("ENTER NAME -> ");
            name = scanner.next();
        } while (!checkingNameAndSurname(name + "/name"));

        String surname;
        do {
            System.out.print("ENTER SURNAME -> ");
            surname = scanner.next();
        } while (!checkingNameAndSurname(surname + "/surname"));

        String startCode = "+998";
        String phone;
        do {
            System.out.print("ENTER PHONE -> " + startCode + " ");
            phone = scanner.next();
        } while (!checkingPhone(phone));
        phone = startCode+phone;

        String login;
        do {
            System.out.print("ENTER LOGIN -> ");
            login = scanner.next();
        } while (!checkingLogin(login));

        String password;
        do {
            System.out.print("☝️ Password must contain at least one uppercase" +
                    " letter, one lowercase letter, one number and be at least 8 characters long\n" +
                    "ENTER PASSWORD -> ");
            password = scanner.next();
        } while (!checkingPassword(password));

        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setName(name);
        profileEntity.setSurname(surname);
        profileEntity.setPhone(phone);
        profileEntity.setLogin(login);
        profileEntity.setPassword(password);
        profileEntity.setRole(ProfileRole.STUDENT);
        profileEntity.setStatus(ProfileStatus.REG_IN);
        profileEntity.setCreatedDate(LocalDateTime.now());
        boolean result = profileRepository.saveStudent(profileEntity);
        if (result) {
            System.out.println("Student saved ✅");
            login();
        }else {
            System.err.println("Student don't saved ❌");
        }
    }

    private boolean checkingPassword(String password) {
        int lower = 0;
        int upper = 0;
        int number = 0;
        for (char c : password.toCharArray()) {
            if (isLowercase(c)){
                lower++;
            } else if (isUppercase(c)) {
                upper++;
            } else if (c > 47 && c < 58) {
                number++;
            }
        }
        if (lower > 0 && upper > 0 && number > 0) {
            return true;
        }
        System.out.println("Password is not valid");
        return false;
    }

    private boolean checkingNameAndSurname(String value){
        String[] strings = value.split("/");
        String checkingValue = strings[0];
        String temp = strings[1];
        if (!isUppercase(checkingValue.charAt(0))){
            System.out.println("The"+temp+"must start with a capital letter");
            return false;
        }

        if (checkingValue.length() < 3){
            System.out.println("Enter your full "+temp);
            return false;
        }
        char[] chars = checkingValue.toCharArray();
        for (char c :chars) {
            if (c == 39 || isUppercase(c) || isLowercase(c)){

            }else {
                System.out.println("There seems to be an error in the "+temp);
                return false;
            }
        }
        return true;
    }

    private boolean checkingPhone(String phone){
        ProfileEntity profile = profileRepository.getProfileByPhone(phone);
        if (profile != null){
            System.out.println("This phone already exists");
            return false;
        }
        if (phone.length() != 9){
            System.out.println("The number is not available");
            return false;
        }

        if (!isNumber(phone)){
            System.out.println("Just enter a number");
            return false;
        }

        return true;
    }

    private boolean checkingLogin(String login){
        ProfileDTO profile = profileRepository.getProfileByLogin(login);
        if (profile != null){
            System.out.println("This login already exists");
            return false;
        }
        if (login.length() < 3){
            System.out.println("Login must be 3 or more characters long");
            return false;
        }
        return true;
    }


    private boolean isUppercase(char c){
        return c > 64 && c < 91;
    }

    private boolean isLowercase(char c){
        return c > 96 && c < 123;
    }

    private boolean isNumber(String phone){
        for (char c : phone.toCharArray()) {
            if (c < 48 || c > 57){
                return false;
            }
        }
        return true;
    }

    public void getStudentList() {
        List<ProfileDTO> profileDTOList = profileRepository.getStudentList();
        profileDTOList.forEach(System.out::println);
    }

    public void searchStudent() {
        System.out.print("ENTER STUDENT id or name or surname or login or phone -> ");
        String value = scanner.next();
        ProfileEntity profileEntity;
        try {
            int id = Integer.parseInt(value);
            profileEntity = profileRepository.getProfileById(id);
        }catch (NumberFormatException e){
            profileEntity = profileRepository.searchStudent(value);
        }
        if (profileEntity == null){
            System.out.println("Student not found");
            return;
        }
        System.out.println(profileEntity);
    }

    public void blockingStudent() {
        System.out.print("ENTER STUDENT ID -> ");
        int id = scanner.nextInt();

        ProfileEntity profileEntity = profileRepository.getProfileById(id);
        if (profileEntity == null){
            System.out.println("Student not found");
            return;
        }
        boolean result = profileRepository.blockingStudent(id);
        if (result){
            System.out.println("Student blocked");
        }else {
            System.out.println("Student not blocked");
        }
    }

    public void activateStudent() {
        System.out.print("ENTER STUDENT ID -> ");
        int id = scanner.nextInt();

        ProfileEntity profileEntity = profileRepository.getProfileById(id);
        if (profileEntity == null){
            System.out.println("Student not found");
            return;
        }
        boolean result = profileRepository.activateStudent(id);
        if (result){
            System.out.println("Student activated");
        }else {
            System.out.println("Student not activated");
        }
    }

    public void studentByBook() {
        System.out.print("ENTER STUDENT ID -> ");
        int id = scanner.nextInt();

        ProfileEntity profileEntity = profileRepository.getProfileById(id);
        if (profileEntity == null){
            System.out.println("Student not found");
            return;
        }

        List<ProfileEntity> profileEntityList = profileRepository.studentByBook(id);
    }

    public void getAllProfileList() {
        List<ProfileDTO> profileDTOList = profileRepository.getProfileList();
        if (profileDTOList.size() == 0){
            System.out.println("Profile list is empty");
            return;
        }
        profileDTOList.forEach(System.out::println);
    }

    public void searchProfile() {
        System.out.print("ENTER PROFILE id or name or surname or login or phone -> ");
        String value = scanner.next();
        ProfileEntity profileEntity;
        try {
            int id = Integer.parseInt(value);
            profileEntity = profileRepository.getProfileById(id);
        }catch (NumberFormatException e){
            profileEntity = profileRepository.searchProfile(value);
        }
        if (profileEntity == null){
            System.out.println("Profile not found");
            return;
        }
        System.out.println(profileEntity);
    }

    public void changeStatus() {
        System.out.print("ENTER PROFILE ID -> ");
        int id = scanner.nextInt();

        ProfileEntity profileEntity = profileRepository.getProfileById(id);
        if (profileEntity == null){
            System.out.println("Profile not found");
            return;
        }

        String value = "BLOCK";
        ProfileStatus status = ProfileStatus.BLOCK;
        if (profileEntity.getStatus().equals(ProfileStatus.BLOCK)) {
            status = ProfileStatus.ACTIVE;
            value = "ACTIVE";
        }

        boolean result = profileRepository.changeStatus(id,status);
        if (result){
            System.out.println("Profile status changed to"+value);
        }else {
            System.out.println("Profile status has not changed");
        }

    }
}
