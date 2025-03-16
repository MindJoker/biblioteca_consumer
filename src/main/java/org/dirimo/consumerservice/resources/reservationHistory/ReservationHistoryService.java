package org.dirimo.consumerservice.resources.reservationHistory;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationHistoryService {

    private final ReservationHistoryRepository repository;

    public void save (ReservationHistory reservationHistory) {
        repository.save(reservationHistory);
    }
}
