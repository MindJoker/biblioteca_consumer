package org.dirimo.consumerservice.resources.reservationHistory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dirimo.commonlibrary.dto.ReservationDTO;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class ReservationHistoryListener {

    private final ObjectMapper objectMapper;
    private final ReservationHistoryService reservationHistoryService;

    @JmsListener(destination = "reservation_history")
    public void receive(String message) {
        try {
            log.info("Ricevuto messaggio JMS: {}", message);
            objectMapper.registerModule(new JavaTimeModule());

            //creare dto distinti

            ReservationDTO reservationDTO = objectMapper.readValue(message, ReservationDTO.class);

            reservationHistoryService.setData(reservationDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
