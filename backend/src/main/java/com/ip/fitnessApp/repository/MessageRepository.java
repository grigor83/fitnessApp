package com.ip.fitnessApp.repository;

import com.ip.fitnessApp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    //@Query("SELECT msg FROM Message msg WHERE msg.reciever.id = :recieverId")
    List<Message> findByRecieverId(Integer recieverId);
}
