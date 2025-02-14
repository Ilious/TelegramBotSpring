package com.example.TelegramBotSpring.service;

import com.example.TelegramBotSpring.config.BotConfig;
import com.example.TelegramBotSpring.config.InitBot;
import com.example.TelegramBotSpring.dto.ReceivedMessage;
import com.example.TelegramBotSpring.repository.MessageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TelegramBotServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private TelegramBotService telegramBotService;


    @Test
    void save() {
        Long userId = 12L;

        ReceivedMessage receivedMessage = ReceivedMessage.builder().userId(userId).build();

        when(telegramBotService.save(12L)).thenReturn(receivedMessage);

        ReceivedMessage savedMessage = telegramBotService.save(userId);

        verify(messageRepository, times(1)).save(any(ReceivedMessage.class));

        Assertions.assertNotNull(savedMessage);
        Assertions.assertEquals(savedMessage.getUserId(), userId);
    }
}