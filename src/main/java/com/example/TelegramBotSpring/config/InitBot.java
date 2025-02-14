package com.example.TelegramBotSpring.config;


import com.example.TelegramBotSpring.exceptions.TelegramException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Arrays;

@Slf4j
@Component
public class InitBot {

    @Autowired
    private TelegramBot telegramBot;

    @EventListener({ContextRefreshedEvent.class})
    public void init() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiException e) {
            log.error(String.format("Error in init bot: %s", Arrays.toString(e.getStackTrace())));
            throw new TelegramException("Error initialization Telegram Bot", e);
        }
    }
}
