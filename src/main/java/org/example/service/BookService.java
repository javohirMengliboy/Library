package org.example.service;

import lombok.Setter;
import org.example.dto.BookDTO;
import org.example.dto.CategoryDTO;
import org.example.entity.BookEntity;
import org.example.entity.CategoryEntity;
import org.example.repository.BookRepository;
import org.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Service
@Setter
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Scanner scannerLine;

    @Autowired
    private Scanner scanner;

    @Autowired
    private Random random;

    public void getBookList(){
        List<BookDTO> bookList = bookRepository.getBookList();
        bookList.forEach((n)->{
            System.out.println("id = "+n.getId()+", title = "+n.getTitle()+", author = "+n.getAuthor()+", category = "+n.getCategoryName());
        });
    }

    public void searchBook() {
        System.out.print("Enter title or author or category  ");
        String value = scannerLine.nextLine();
        List<BookDTO> bookList = bookRepository.searchBook(value);
        if (bookList.size() == 0){
            System.err.println("Nothing found \uD83E\uDD37\uD83C\uDFFC\u200D♂\uFE0F");
            return;
        }
        bookList.forEach((n)->{
            System.out.println("id = "+n.getId()+", title = "+n.getTitle()+", author = "+n.getAuthor()+", category = "+n.getCategoryName());
        });
    }

    public void getBookListByCategory() {
        List<CategoryDTO> categoryList = categoryRepository.getCategoryList();
        categoryList.forEach((n)->{
            System.out.println(n.getId()+". "+n.getName());
        });
        int id;
        while (true){
            System.out.print("Choose id  ");
            id = scanner.nextInt();
            boolean bool = false;
            for (CategoryDTO c : categoryList) {
                if (id == c.getId()){
                    bool = true;
                    break;
                }
            }
            if (bool){
                break;
            }
        }
        List<BookDTO> bookList = bookRepository.getBookListByCategory(id);
        if (bookList.size() == 0){
            System.err.println("Nothing found \uD83E\uDD37\uD83C\uDFFC\u200D♂\uFE0F");
            return;
        }
        bookList.forEach((n)->{
            System.out.println("id = "+n.getId()+", title = "+n.getTitle()+", author = "+n.getAuthor()+", category = "+n.getCategoryName());
        });

    }

    public void addBook() {
        System.out.print("ENTER BOOK TITLE -> ");
        String title = scannerLine.nextLine();

        System.out.print("ENTER BOOK AUTHOR -> ");
        String author = scannerLine.nextLine();

        System.out.print("ENTER BOOK CATEGORY -> ");
        String categoryName = scannerLine.nextLine();
        CategoryEntity categoryEntity = categoryRepository.getCategoryByName(categoryName);
        if (categoryEntity == null){
            System.out.println("Category not found");
            return;
        }
        int rand = random.nextInt(1,24);
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(title);
        bookEntity.setAuthor(author);
        bookEntity.setCategory(categoryEntity);
        bookEntity.setVisible(true);
        bookEntity.setCreatedDate(LocalDateTime.now());
        bookEntity.setPublishedDate(bookEntity.getCreatedDate().minusMonths(rand));
        boolean result = bookRepository.addBook(bookEntity);
    }

    public void removeBook() {
        System.out.print("ENTER BOOK ID -> ");
        int id = scanner.nextInt();
        boolean result = bookRepository.removeBook(id);
    }
}
