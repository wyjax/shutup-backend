package com.wyjax.shutup.chat.repository;

import com.wyjax.shutup.chat.domain.ChatMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByChatRoomUuid(String uuid, Pageable pageable);
}
