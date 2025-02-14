package com.example.TelegramBotSpring.config;


import com.example.TelegramBotSpring.exceptions.TextException;
import com.example.TelegramBotSpring.listeners.EventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final EventListener eventListener;
    private final BotConfig botConfig;

    public TelegramBot(BotConfig config, EventListener listener) {
        super(config.getToken());
        this.botConfig = config;
        this.eventListener = listener;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update == null || !update.hasMessage() || !update.getMessage().hasText()
                || !update.getMessage().isUserMessage())
            return;

        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText("You're welcome");
            executeAsync(sendMessage);
        } catch (TelegramApiException exception) {
            log.warn(String.format("Exception in handle: %s", Arrays.toString(exception.getStackTrace())));
            throw new TextException(String.format("Exception in handle: %s", update.getMessage()), exception.getCause());
        }

        eventListener.handleMessage(update);

    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }
}
