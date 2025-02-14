package com.example.TelegramBotSpring.listeners;

import com.example.TelegramBotSpring.service.handler.Handler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventListenerTest {

    @Mock
    private List<Handler> eventHandlers;

    @Mock
    private Handler handlerMock;

    @Mock
    private Update updateMock;

    @InjectMocks
    private EventListener eventListener;

    @BeforeEach
    void init() {
        eventListener = new EventListener(List.of(handlerMock));
    }

    @Test
    void handleUpdate() {
        eventListener.handleMessage(updateMock);

        verify(handlerMock, times(1)).handle(updateMock);
    }
}