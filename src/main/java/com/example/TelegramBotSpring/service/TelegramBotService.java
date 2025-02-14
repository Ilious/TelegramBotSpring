package com.example.TelegramBotSpring.service;

import com.example.TelegramBotSpring.dto.ReceivedMessage;
import com.example.TelegramBotSpring.repository.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class TelegramBotService {

    private final MessageRepository messageRepository;

    @Transactional
    public ReceivedMessage save(long id) {
        return messageRepository.findByUserId(id)
                .map(message -> {
                    message.setReceivedAt(new Timestamp(System.currentTimeMillis()));
                    return messageRepository.save(message);
                })
                .orElseGet(() -> messageRepository.save(ReceivedMessage.builder().userId(id).build()));
    }
}
