package com.example.TelegramBotSpring.service.handler;


import org.telegram.telegrambots.meta.api.objects.Update;

public interface Handler {

    void handle(Update message);
}
