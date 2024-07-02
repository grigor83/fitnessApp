package com.ip.fitnessApp.sheduledTask;

import com.ip.fitnessApp.model.Category;
import com.ip.fitnessApp.model.FitnessProgram;
import com.ip.fitnessApp.model.User;
import com.ip.fitnessApp.model.Subscription;
import com.ip.fitnessApp.repository.FitnessProgramRepository;
import com.ip.fitnessApp.repository.SubscriptionRepository;
import com.ip.fitnessApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SendSubscriptions {

    @Autowired
    private FitnessProgramRepository fitnessProgramRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 23 * * ?", zone = "Europe/Berlin")
    public void runTask() {
        System.out.println("Running Scheduled Task  every day at 23h!");
        LocalDateTime twentyFourHoursAgo = LocalDateTime.now().minusHours(24);
        List<FitnessProgram> programs = fitnessProgramRepository.findByCreatedAtAfter(twentyFourHoursAgo);

        List<Subscription> subs = subscriptionRepository.findAll();
        subs.forEach(subscription -> {
            User user = subscription.getUser();
            Category category = subscription.getCategory();
            programs.forEach(program -> {
                if (program.getCategory().getId() == category.getId())
                    emailService.sendSubscription(user, program);
            });
        });
    }

}

