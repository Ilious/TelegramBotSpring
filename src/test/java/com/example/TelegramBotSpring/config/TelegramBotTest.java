package com.example.TelegramBotSpring.config;

import com.example.TelegramBotSpring.listeners.EventListener;
import com.example.TelegramBotSpring.service.handler.Handler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TelegramBotTest {

    @Mock
    Update update;

    @Mock
    Message message;

    @Mock
    EventListener eventListener;

    @Mock
    BotConfig botConfig;

    @InjectMocks
    TelegramBot telegramBot;

    @BeforeEach
    void init() {
        telegramBot = new TelegramBot(botConfig, eventListener);
    }

    @Test
    void onUpdateReceived_messageHasNotMessage() {
        when(update.hasMessage()).thenReturn(false);

        telegramBot.onUpdateReceived(update);

        verify(eventListener, times(0)).handleMessage(update);
    }

    @Test
    void onUpdateReceived_messageHasNotText() {
        when(update.hasMessage()).thenReturn(true);
        when(update.getMessage()).thenReturn(message);
        when(update.getMessage().hasText()).thenReturn(false);

        telegramBot.onUpdateReceived(update);

        verify(eventListener, times(0)).handleMessage(update);
    }

    @Test
    void onUpdateReceived_messageIsFromBot() {
        when(update.hasMessage()).thenReturn(true);
        when(update.getMessage()).thenReturn(message);
        when(update.getMessage().hasText()).thenReturn(true);
        when(update.getMessage().isUserMessage()).thenReturn(false);

        telegramBot.onUpdateReceived(update);

        verify(eventListener, times(0)).handleMessage(update);
    }

    @Test
    void onUpdateReceived_handleMessage() {
        when(update.hasMessage()).thenReturn(true);
        when(update.getMessage()).thenReturn(message);
        when(update.getMessage().hasText()).thenReturn(true);
        when(update.getMessage().isUserMessage()).thenReturn(true);

        telegramBot.onUpdateReceived(update);

        verify(eventListener, times(1)).handleMessage(update);
    }
}