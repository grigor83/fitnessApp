package com.ip.fitnessApp.repository;

import com.ip.fitnessApp.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    //@Query("SELECT pretplata FROM Subscription pretplata WHERE pretplata.user.id = :userId")
    List<Subscription> findByUserId(Integer userId);
}
