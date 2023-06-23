package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@ToString
@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author")
    private String author;

    @ManyToOne
    @JoinColumn (name = "category_id")
    private CategoryEntity category;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "published_date")
    private LocalDateTime publishedDate;

    @Column(name = "available_date")
    private Integer availableDate;

    @Column(name = "visible")
    private Boolean visible;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "bookEntity")
    private List<TakenBookEntity> takenBookEntities;

}
