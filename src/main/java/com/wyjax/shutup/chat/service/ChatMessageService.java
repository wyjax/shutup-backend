package com.wyjax.shutup.chat.service;

import com.wyjax.shutup.chat.domain.ChatMessage;
import com.wyjax.shutup.chat.domain.ChatRoom;
import com.wyjax.shutup.chat.model.ChatMessageModel;
import com.wyjax.shutup.chat.repository.ChatMessageRepository;
import com.wyjax.shutup.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;


    @Transactional(readOnly = true)
    public List<ChatMessageModel> geChatMessages(String uuid, Pageable pageable) {
        return chatMessageRepository.findByChatRoomUuid(uuid, pageable).stream()
                .map(chatMessage -> {
                    ChatMessageModel model = new ChatMessageModel();
                    model.setName(chatMessage.getNickName());
                    model.setLoginId(chatMessage.getLoginId());
                    model.setContent(chatMessage.getContent());
                    return model;
                }).collect(Collectors.toList());
    }

    @Transactional
    public void saveMessage(ChatMessageModel chatMessage) {
        ChatRoom chatRoom = chatRoomRepository.findByUuid(chatMessage.getUuid())
                .orElseThrow(() -> new NoSuchElementException("not found"));
        ChatMessage message = ChatMessage.builder()
                .chatRoom(chatRoom)
                .nickName(chatMessage.getName())
                .loginId(chatMessage.getLoginId())
                .content(chatMessage.getContent())
                .build();
        chatMessageRepository.save(message);
    }
}
