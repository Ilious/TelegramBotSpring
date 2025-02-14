package com.example.TelegramBotSpring.service.handler;

import com.example.TelegramBotSpring.service.TelegramBotService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventHandlerTest {

    @Mock
    private TelegramBotService telegramBotService;

    @InjectMocks
    private EventHandler eventHandler;

    @Test
    void handle() {
        long userId = 123L;
        Update updateMock = mock(Update.class);
        Message messageMock = mock(Message.class);
        User userMock = mock(User.class);

        when(updateMock.getMessage()).thenReturn(messageMock);
        when(updateMock.getMessage().getFrom()).thenReturn(userMock);
        when(updateMock.getMessage().getFrom().getId()).thenReturn(userId);

        Assertions.assertDoesNotThrow(() -> eventHandler.handle(updateMock));

        verify(telegramBotService, times(1)).save(userId);
    }
}