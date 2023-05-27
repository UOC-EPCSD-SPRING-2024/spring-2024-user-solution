package edu.uoc.epcsd.user.domain.service;

import edu.uoc.epcsd.user.domain.Alert;

import java.util.List;
import java.util.Optional;

public interface AlertService {

    List<Alert> findAllAlerts();

    Optional<Alert> findAlertById(Long id);

    Long createAlert(Alert alert);

}
