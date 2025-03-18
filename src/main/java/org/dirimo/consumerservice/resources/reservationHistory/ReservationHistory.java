package org.dirimo.consumerservice.resources.reservationHistory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dirimo.consumerservice.resources.book.Book;
import org.dirimo.consumerservice.resources.customer.Customer;

import java.time.LocalDate;

@Entity
@Table(name = "Reservation_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationHistory {

    @Id
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "DATA_START")
    private LocalDate resStartDate;

    @Column(name = "DATA_END")
    private LocalDate resEndDate;
}
