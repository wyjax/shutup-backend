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

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;


    @Transactional(readOnly = true)
    public List<ChatMessageModel> geChatMessages(String uuid, Pageable pageable) {
        List<ChatMessageModel> chatMessageModels = new ArrayList<>();
        List<ChatMessage> messages = chatMessageRepository.findByChatRoomUuidOrderByIdDesc(uuid, pageable);
        for (int i = messages.size() - 1; i >= 0; i--) {
            ChatMessageModel model = new ChatMessageModel();
            model.setName(messages.get(i).getNickName());
            model.setLoginId(messages.get(i).getLoginId());
            model.setContent(messages.get(i).getContent());
            chatMessageModels.add(model);
        }
        return chatMessageModels;
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
