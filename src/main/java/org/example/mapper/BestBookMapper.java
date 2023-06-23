package org.example.mapper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BestBookMapper {
    private Integer id;
    private String title;
    private String author;
    private String categoryName;
    private Integer takenCount;

    public BestBookMapper(Integer id, String title, String author, String categoryName, Integer takenCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.categoryName = categoryName;
        this.takenCount = takenCount;
    }
}
