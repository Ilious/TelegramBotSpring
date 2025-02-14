package com.example.TelegramBotSpring.exceptions;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TelegramException extends RuntimeException {

    private final String message;
    private final Throwable error;

    public TelegramException(String message, Throwable error) {
        super(message, error);
        this.error = error;
        this.message = message;
    }

    @Override
    public String getLocalizedMessage() {
        return message;
    }
}
