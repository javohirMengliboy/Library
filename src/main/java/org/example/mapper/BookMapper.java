package org.example.mapper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
public class BookMapper {
    private Integer id;
    private String title;
    private String author;
    private String categoryName;
    private LocalDateTime createdDate;

    public BookMapper(Integer id, String title, String author, String categoryName, LocalDateTime takenDate, Integer available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.categoryName = categoryName;
        this.createdDate = takenDate;
    }
}
