package org.dirimo.consumerservice.resources.reservationHistory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dirimo.consumerservice.resources.book.Book;
import org.dirimo.consumerservice.resources.book.BookRepository;
import org.dirimo.consumerservice.resources.customer.Customer;
import org.dirimo.consumerservice.resources.customer.CustomerRepository;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class ReservationHistoryListener {

    private final ReservationHistoryRepository reservationHistoryRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper;

    @JmsListener(destination = "reservation_history")
    public void receive (String message) {
        try {
            log.info("Ricevuto messaggio JMS: {}", message);
            objectMapper.registerModule(new JavaTimeModule());
            ReservationHistoryPayload payload = objectMapper.readValue(message, ReservationHistoryPayload.class);

            Customer customer = customerRepository.findById(payload.getCustomer().getCustomerId())
                    .orElseGet(() -> customerRepository.save(
                            new Customer(payload.getCustomer().getCustomerId(),
                                    payload.getCustomer().getFirstName(),
                                    payload.getCustomer().getLastName(),
                                    payload.getCustomer().getEmail())
                    ));

            Book book = bookRepository.findById(payload.getBook().getBookId())
                    .orElseGet(() -> bookRepository.save(
                            new Book(payload.getBook().getBookId(),
                                    payload.getBook().getIsbn(),
                                    payload.getBook().getTitle(),
                                    payload.getBook().getAuthor(),
                                    payload.getBook().getYear(),
                                    payload.getBook().getGenre(),
                                    payload.getBook().getPublisher(),
                                    payload.getBook().getLanguage(),
                                    payload.getBook().getDescription())
                    ));

            reservationHistoryRepository.save(
                    new ReservationHistory(payload.getReservationId(), customer, book,
                            payload.getResStartDate(), payload.getResEndDate())
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
