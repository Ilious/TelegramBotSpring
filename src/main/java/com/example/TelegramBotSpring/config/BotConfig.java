package com.example.TelegramBotSpring.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("application.yaml")
public class BotConfig {

    @Value("${telegram.data.token}")
    private String token;

    @Value("${telegram.data.username}")
    private String botName;
}
