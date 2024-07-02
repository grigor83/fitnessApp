package com.ip.fitnessApp.service;

import com.ip.fitnessApp.model.*;
import com.ip.fitnessApp.repository.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> findByReceiverId(Integer recieverId){
        return messageRepository.findByRecieverId(recieverId);
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }


}
