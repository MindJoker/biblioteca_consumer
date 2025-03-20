package org.dirimo.consumerservice.resources.reservationHistory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.dirimo.commonlibrary.dto.BookDTO;
import org.dirimo.commonlibrary.dto.CustomerDTO;
import org.dirimo.commonlibrary.dto.ReservationDTO;
import org.dirimo.consumerservice.resources.book.Book;
import org.dirimo.consumerservice.resources.book.BookRepository;
import org.dirimo.consumerservice.resources.customer.Customer;
import org.dirimo.consumerservice.resources.customer.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationHistoryService {

    private final ReservationHistoryRepository reservationHistoryRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;

    public ReservationHistory create(ReservationHistory reservationHistory) {
        return reservationHistoryRepository.save(reservationHistory);
    }

    public List<ReservationHistory> getAll() {
        return reservationHistoryRepository.findAll();
    }

    //servizio per popolare la reservationHistory

    public ReservationHistory setData(ReservationDTO reservationDTO) {
        CustomerDTO customerDTO = reservationDTO.getCustomer();
        BookDTO bookDTO = reservationDTO.getBook();


        Customer customer = customerRepository.findById(customerDTO.getId())
                .orElseGet(() -> customerRepository.save(
                        new Customer(customerDTO.getId(),
                                customerDTO.getFirstName(),
                                customerDTO.getLastName(),
                                customerDTO.getEmail(),
                                null
                        )));

        Book book = bookRepository.findById(bookDTO.getId())
                .orElseGet(() -> bookRepository.save(
                        new Book(bookDTO.getId(),
                                bookDTO.getIsbn(),
                                bookDTO.getTitle(),
                                bookDTO.getAuthor(),
                                bookDTO.getYear(),
                                bookDTO.getGenre(),
                                bookDTO.getPublisher(),
                                bookDTO.getLanguage(),
                                bookDTO.getDescription(),
                                null
                        )));

        return create(
                new ReservationHistory(reservationDTO.getId(), customer, book,
                        reservationDTO.getResStartDate(), reservationDTO.getResEndDate())
        );
    }

    public void convertData(String payload) throws JsonProcessingException {
        objectMapper.registerModule(new JavaTimeModule());
        ReservationDTO reservationDTO = objectMapper.readValue(payload, ReservationDTO.class);
        setData(reservationDTO);
    }
}
