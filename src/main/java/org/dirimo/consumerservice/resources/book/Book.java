package org.dirimo.consumerservice.resources.book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.dirimo.consumerservice.resources.reservationHistory.ReservationHistory;

import java.util.List;

@Entity
@Table(name = "Books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "GENRE")
    private String genre;

    @Column(name = "PUBLISHER")
    private String publisher;

    @Column(name = "LANGUAGE")
    private String language;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<ReservationHistory> reservation;

    public Book(Long bookId, String isbn, String title, String author, Integer year, String genre, String publisher, String language, String description) {
        this.id = bookId;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.publisher = publisher;
        this.language = language;
        this.description = description;
    }
}
