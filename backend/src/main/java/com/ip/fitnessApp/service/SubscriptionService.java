package com.ip.fitnessApp.service;

import com.ip.fitnessApp.model.Subscription;
import com.ip.fitnessApp.repository.SubscriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<Subscription> findByUserId(Integer userId){
        return subscriptionRepository.findByUserId(userId);
    }

    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public boolean deleteById(Integer id) {
        Optional<Subscription> subscription = subscriptionRepository.findById(id);
        if (subscription.isEmpty())
            return false;
        else{
            subscriptionRepository.deleteById(id);
            return true;
        }
    }
}
