package com.example.TelegramBotSpring.service.handler;

import com.example.TelegramBotSpring.service.TelegramBotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class EventHandler implements Handler {

    private final TelegramBotService telegramBotService;

    @Override
    public void handle(Update message) {
        Long id = message.getMessage().getFrom().getId();

        telegramBotService.save(id);
    }
}
