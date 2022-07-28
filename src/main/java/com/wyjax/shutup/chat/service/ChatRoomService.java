package com.wyjax.shutup.chat.service;

import com.wyjax.shutup.chat.domain.ChatRoom;
import com.wyjax.shutup.chat.model.ChatRoomModel;
import com.wyjax.shutup.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    @Transactional(readOnly = true)
    public List<ChatRoomModel> getChatRooms() {
        return chatRoomRepository.findAll().stream()
                .map(ChatRoomModel::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ChatRoomModel getChatRoom(Long chatRoomId) {
        ChatRoom chatRoom = findChatRoom(chatRoomId);
        return new ChatRoomModel(chatRoom);
    }

    @Transactional
    public ChatRoomModel addChatRoom(ChatRoomModel model) {
        ChatRoom chatRoom = ChatRoom.builder()
                .name(model.getName())
                .uuid(String.valueOf(UUID.randomUUID()))
                .build();
        chatRoomRepository.save(chatRoom);
        return new ChatRoomModel(chatRoom);
    }

    @Transactional
    public ChatRoomModel updateChatRoom(Long chatRoomId, ChatRoomModel model) {
        ChatRoom chatRoom = findChatRoom(chatRoomId);
        chatRoom.update(model);
        chatRoomRepository.save(chatRoom);
        return new ChatRoomModel(chatRoom);
    }

    @Transactional
    public void removeChatRoom(Long chatRoomId) {
        ChatRoom chatRoom = findChatRoom(chatRoomId);
        chatRoomRepository.delete(chatRoom);
    }

    private ChatRoom findChatRoom(Long chatRoomId) {
        Optional<ChatRoom> optional = chatRoomRepository.findById(chatRoomId);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("엔티티를 찾을 수 없습니다.");
    }
}
