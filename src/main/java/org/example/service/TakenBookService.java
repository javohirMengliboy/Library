package org.example.service;

import lombok.Setter;
import org.example.container.ComponentContainer;
import org.example.dto.BookDTO;
import org.example.entity.BookEntity;
import org.example.entity.ProfileEntity;
import org.example.entity.TakenBookEntity;
import org.example.enums.TakenBookStatus;
import org.example.mapper.BookMapper;
import org.example.repository.BookRepository;
import org.example.repository.ProfileRepository;
import org.example.repository.TakenBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Service
@Setter
public class TakenBookService {
    @Autowired
    private TakenBookRepository takenBookRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private Scanner scanner;

    public void takeBook() {
        List<BookDTO> bookDTOList = bookRepository.getBookList();
        BookEntity bookEntity = getTempBookId(bookDTOList);

        ProfileEntity profileEntity = profileRepository.getProfileById(ComponentContainer.profileDTO.getId());
        TakenBookEntity takenBookEntity = new TakenBookEntity();
        takenBookEntity.setProfileEntity(profileEntity);
        takenBookEntity.setBookEntity(bookEntity);
        takenBookEntity.setStatus(TakenBookStatus.TOOK);
        takenBookEntity.setCreatedDate(LocalDateTime.now());
        boolean result = takenBookRepository.saveTakenBook(takenBookEntity);
        if (result){
            System.out.println("Taken book saved ✅");
        }else {
            System.err.println("Taken book don't saved ❌");
        }
    }

    public void returnBook() {
        List<BookMapper> bookMapperList = takenBookRepository.getBookListByProfileId(ComponentContainer.profileDTO.getId());
        BookEntity bookEntity = getTempBookId(bookMapperList);

        ProfileEntity profileEntity = profileRepository.getProfileById(ComponentContainer.profileDTO.getId());
        TakenBookEntity takenBookEntity = new TakenBookEntity();
        takenBookEntity.setProfileEntity(profileEntity);
        takenBookEntity.setBookEntity(bookEntity);
        takenBookEntity.setStatus(TakenBookStatus.RETURNED);
        takenBookEntity.setCreatedDate(LocalDateTime.now());
        boolean result = takenBookRepository.saveTakenBook(takenBookEntity);
        if (result){
            System.out.println("Return book saved ✅");
        }else {
            System.err.println("Return book don't saved ❌");
        }


    }

    public <T> BookEntity getTempBookId(List<T> list){
        BookEntity bookEntity;
        while (true){
            System.out.print("Choose book id -> ");
            int id = scanner.nextInt();
            bookEntity = bookRepository.getBookEntityById(id);
            if (bookEntity == null){
                System.out.println("Wrong selection");
            }else {
                break;
            }
        }
        return bookEntity;
    }

    public void booksOnHand() {
        List<BookMapper> bookMapperList = takenBookRepository.getBookListByProfileId(ComponentContainer.profileDTO.getId());
        bookMapperList.forEach(System.out::println);
    }

    public void takeBookHistory() {
        List<Object[]> bookMapperList = takenBookRepository.getTakenBookHistory(ComponentContainer.profileDTO.getId());
        bookMapperList.forEach(System.out::println);
    }

    public void getTakenBooks() {
        List<Object[]> takenBookMapperList = takenBookRepository.getTakenBookMapperList();
        if (takenBookMapperList.size() == 0){
            System.out.println("Not taken book yet");
        }
        takenBookMapperList.forEach(System.out::println);
    }

    public void getBookHistory() {
        System.out.print("ENTER BOOK ID -> ");
        int id = scanner.nextInt();
        BookEntity bookEntity = bookRepository.getBookEntityById(id);
        if (bookEntity == null){
            System.out.println("Book not found");
            return;
        }
        List<Object[]> bookHistoryMapperList = takenBookRepository.getBookHistory(id);
        bookHistoryMapperList.forEach(System.out::println);
    }

    public void getBestBookList() {
        List<Object[]> bestBookMapperList = takenBookRepository.getBestBookList();
        bestBookMapperList.forEach(System.out::println);
    }
}
