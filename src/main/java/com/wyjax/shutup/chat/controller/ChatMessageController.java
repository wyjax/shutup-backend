package com.wyjax.shutup.chat.controller;

import com.wyjax.shutup.chat.model.ChatMessageModel;
import com.wyjax.shutup.chat.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatMessageController {
    private final SimpMessageSendingOperations convertAndSend;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageModel sendMessage(@Payload ChatMessageModel chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    public ChatMessageModel addUser(@Payload ChatMessageModel chatMessage,
                                    SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getName());
        return chatMessage;
    }

    @MessageMapping("/chat/{uuid}")
    public void chat(@DestinationVariable String uuid, @Payload ChatMessageModel chatMessage) {
        chatMessage.setUuid(uuid);
        chatMessageService.saveMessage(chatMessage);
        convertAndSend.convertAndSend("/sub/chat/" + uuid, chatMessage);
    }
}
