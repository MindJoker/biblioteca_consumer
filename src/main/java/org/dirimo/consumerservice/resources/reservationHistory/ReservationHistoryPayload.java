package org.dirimo.consumerservice.resources.reservationHistory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationHistoryPayload {
    private Long reservationId;
    private BookPayload book;
    private CustomerPayload customer;
    private LocalDate resStartDate;
    private LocalDate resEndDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public static class BookPayload {
    private Long bookId;
    private String isbn;
    private String title;
    private String author;
    private Integer year;
    private String genre;
    private String publisher;
    private String language;
    private String description;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
public static class CustomerPayload {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;

}
}
