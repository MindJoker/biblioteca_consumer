package org.dirimo.consumerservice.resources.reservationHistory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dirimo.commonlibrary.dto.ReservationDTO;
import org.dirimo.commonlibrary.event.EventType;
import org.dirimo.commonlibrary.event.GenericModuleEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class ReservationHistoryListener {

    private final ReservationHistoryService reservationHistoryService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @JmsListener(destination = "reservation_history")
    public void receive(String message) {
        log.info("Ricevuto messaggio JMS: {}", message);
        applicationEventPublisher.publishEvent(new GenericModuleEvent<>(this, EventType.RECEIVED, message));
    }

    @EventListener
    public void onReceivedMessage(GenericModuleEvent<String> genericModuleEvent) throws JsonProcessingException {
        if (genericModuleEvent.getEventType().equals(EventType.RECEIVED)) {
            String payload = genericModuleEvent.getPayload();
            reservationHistoryService.convertData(payload);
        }
    }
}
