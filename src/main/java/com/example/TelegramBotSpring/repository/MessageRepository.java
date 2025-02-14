package com.example.TelegramBotSpring.repository;


import com.example.TelegramBotSpring.dto.ReceivedMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<ReceivedMessage, Long> {

    Optional<ReceivedMessage> findByUserId(Long chatId);
}
