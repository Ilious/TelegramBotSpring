package com.example.TelegramBotSpring.exceptions;


import lombok.Getter;

@Getter
public class TextException extends TelegramException {

    private final String message;
    private final Throwable throwable;

    public TextException(String message, Throwable throwable) {
        super(message, throwable);
        this.throwable = throwable;
        this.message = message;
    }
}
