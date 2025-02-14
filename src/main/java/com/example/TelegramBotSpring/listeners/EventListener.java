package com.example.TelegramBotSpring.listeners;

import com.example.TelegramBotSpring.service.handler.Handler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventListener {

    private final List<Handler> eventHandlers;

    @Async
    public void handleMessage(Update update) {
        log.info("Bot has got a new update: " + update.getMessage());

        eventHandlers.forEach(handler -> handler.handle(update));

        log.info("Update handled: " + update.getMessage());
    }
}
