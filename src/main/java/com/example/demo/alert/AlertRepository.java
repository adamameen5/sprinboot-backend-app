package com.example.demo.alert;

import com.example.demo.currency.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    @Query("SELECT a FROM Alert a WHERE a.createdByUser=?1")
    Optional<Alert> findAlertByCreatedByUser(String createdByUser);

    @Query("SELECT a FROM Alert a WHERE a.name=?1")
    Optional<Alert> findAlertByName(String name);

}
