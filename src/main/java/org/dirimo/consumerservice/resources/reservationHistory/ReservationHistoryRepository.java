package org.dirimo.consumerservice.resources.reservationHistory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationHistoryRepository extends JpaRepository<ReservationHistory, Long> {
}
