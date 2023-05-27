package edu.uoc.epcsd.user.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SpringDataAlertRepository extends JpaRepository<AlertEntity, Long> {

    List<AlertEntity> findAllByProductIdAndFromLessThanEqualAndToGreaterThanEqual(Long productId, LocalDate from, LocalDate to);
}
