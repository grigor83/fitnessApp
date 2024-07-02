package com.ip.fitnessApp.controllers;

import com.ip.fitnessApp.model.Subscription;
import com.ip.fitnessApp.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Subscription>> findByUserId(@PathVariable Integer userId) {
        List<Subscription> subs = subscriptionService.findByUserId(userId);
        return new ResponseEntity<>(subs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Subscription> save(@RequestBody Subscription subscription) {
        Subscription newSubscription = subscriptionService.createSubscription(subscription);
        return new ResponseEntity<>(newSubscription, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (subscriptionService.deleteById(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }
}
