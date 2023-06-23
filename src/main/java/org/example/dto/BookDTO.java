package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
public class BookDTO {
    private Integer id;
    private String title;
    private String author;
    private String categoryName;
    private LocalDateTime publishedDate;
    private Integer availableDate;
    private Boolean visible;
    private LocalDateTime createdDate;

    public BookDTO(Integer id, String title, String author, String categoryName, LocalDateTime publishedDate, Integer availableDate, Boolean visible, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.categoryName = categoryName;
        this.publishedDate = publishedDate;
        this.availableDate = availableDate;
        this.visible = visible;
        this.createdDate = createdDate;
    }
}
