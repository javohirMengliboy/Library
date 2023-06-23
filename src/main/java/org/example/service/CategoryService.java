package org.example.service;

import lombok.Setter;
import org.example.dto.CategoryDTO;
import org.example.entity.CategoryEntity;
import org.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Service
@Setter
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Scanner scanner;
    public void getCategoryList() {
        List<CategoryDTO> categoryDTOList = categoryRepository.getCategoryList();
        categoryDTOList.forEach(System.out::println);
    }

    public void addCategory() {
        System.out.print("ENTER CATEGORY NAME -> ");
        String name = scanner.next();
        CategoryEntity categoryEntity = categoryRepository.getCategoryByName(name);
        if (categoryEntity != null){
            System.out.println("This category already exists");
            return;
        }
        categoryEntity.setName(name);
        categoryEntity.setVisible(true);
        categoryEntity.setCreatedDate(LocalDateTime.now());

        boolean result = categoryRepository.addCategory(categoryEntity);
        if (result){
            System.out.println("Category added");
        }else {
            System.out.println("Category not added");
        }
    }
}
