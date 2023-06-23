package org.example.mapper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class TakenBookMapper {
    private Integer id;
    private String title;
    private String author;
    private String categoryName;
    private LocalDateTime takenDate;
    private LocalDateTime deadLineDate;
    private String studentName;
    private String studentSurname;
    private String phone;

    public TakenBookMapper(Integer id, String title, String author, String categoryName, LocalDateTime takenDate, LocalDateTime deadLineDate, String studentName, String studentSurname, String phone) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.categoryName = categoryName;
        this.takenDate = takenDate;
        this.deadLineDate = deadLineDate;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.phone = phone;
    }
}
