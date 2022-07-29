package com.wyjax.shutup.chat.controller;

import com.wyjax.shutup.chat.model.ChatMessageModel;
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

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageModel sendMessage(@Payload ChatMessageModel chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    public ChatMessageModel addUser(@Payload ChatMessageModel chatMessage,
                                    SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/chat/{room}")
    public void chat(@DestinationVariable String room, @Payload ChatMessageModel chatMessage) {
        System.out.println("chat message");
        convertAndSend.convertAndSend("/sub/chat/" + room, chatMessage);
    }
}
