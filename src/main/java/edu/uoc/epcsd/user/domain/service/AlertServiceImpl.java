package edu.uoc.epcsd.user.domain.service;

import edu.uoc.epcsd.user.application.rest.response.GetProductResponse;
import edu.uoc.epcsd.user.domain.Alert;
import edu.uoc.epcsd.user.domain.repository.AlertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class AlertServiceImpl implements AlertService {

    private final AlertRepository alertRepository;

    @Value("${productService.getProductDetails.url}")
    private String productServiceUrl;


    public List<Alert> findAllAlerts() {
        return alertRepository.findAllAlerts();
    }

    public Optional<Alert> findAlertById(Long id) {
        return alertRepository.findAlertById(id);
    }

    public Long createAlert(Alert alert) {

        // check that the product exists
        new RestTemplate().getForEntity(productServiceUrl, GetProductResponse.class, alert.getProductId()).getBody();

        return alertRepository.createAlert(alert);
    }
}
