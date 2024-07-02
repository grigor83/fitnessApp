package com.ip.fitnessApp.controllers;

import com.ip.fitnessApp.model.Message;
import com.ip.fitnessApp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;
    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    @GetMapping("/{receiverId}")
    public ResponseEntity<List<Message>> findByReceiverId(@PathVariable Integer receiverId) {
        List<Message> receivedMessages = messageService.findByReceiverId(receiverId);
        return new ResponseEntity<>(receivedMessages, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Message> save(@RequestBody Message message) {
        Message newMessage = messageService.createMessage(message);
        return new ResponseEntity<>(newMessage, HttpStatus.CREATED);
    }

}
