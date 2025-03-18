package org.dirimo.consumerservice.resources.reservationHistory;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Transactional
@RestController
@RequiredArgsConstructor
@RequestMapping("ReservationHistory")
public class ReservationHistoryController {

    private final ReservationHistoryService reservationHistoryService;

    @GetMapping("/")
    public List<ReservationHistory> getReservationHistory() {
        return reservationHistoryService.getAll();
    }
}
