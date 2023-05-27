package edu.uoc.epcsd.user.domain.repository;


import edu.uoc.epcsd.user.domain.Alert;
import org.springframework.beans.PropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AlertRepository {

//    @Query("select a from Alert a where a.productId = ?1 and a.from <= ?2 and a.to >= ?2")
//    List<Alert> findAlertsByProductIdAndInterval(Long productId, LocalDate availableOnDate);

    List<Alert> findAllAlerts();

    Optional<Alert> findAlertById(Long id);

    List<Alert> findAlertsByProductIdAndDate(Long productId, LocalDate availableOnDate);

    Long createAlert(Alert alert);

}
