package edu.uoc.epcsd.user.infrastructure.repository.jpa;


import edu.uoc.epcsd.user.domain.Alert;
import edu.uoc.epcsd.user.domain.repository.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AlertRepositoryImpl implements AlertRepository {

    private final SpringDataAlertRepository jpaRepository;

    private final SpringDataUserRepository jpaUserRepository;

    @Override
    public List<Alert> findAllAlerts() {
        return jpaRepository.findAll().stream().map(AlertEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Alert> findAlertById(Long id) {
        return jpaRepository.findById(id).map(AlertEntity::toDomain);
    }

    @Override
    public List<Alert> findAlertsByProductIdAndDate(Long productId, LocalDate availableOnDate) {
        return jpaRepository.findAllByProductIdAndFromLessThanEqualAndToGreaterThanEqual(productId, availableOnDate, availableOnDate).stream().map(AlertEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Long createAlert(Alert alert) {

        UserEntity userEntity =  jpaUserRepository.findById(alert.getUserId()).orElseThrow(IllegalArgumentException::new);

        AlertEntity alertEntity = AlertEntity.fromDomain(alert);
        alertEntity.setUser(userEntity);

        return jpaRepository.save(alertEntity).getId();
    }



//    @Query("select a from Alert a where a.productId = ?1 and a.from <= ?2 and a.to >= ?2")
//    List<Alert> findAlertsByProductIdAndInterval(Long productId, LocalDate availableOnDate);

}
