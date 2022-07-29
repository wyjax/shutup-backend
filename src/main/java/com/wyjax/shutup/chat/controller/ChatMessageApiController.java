package com.wyjax.shutup.chat.controller;

import com.wyjax.shutup.chat.model.ChatMessageModel;
import com.wyjax.shutup.chat.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatMessageApiController {
    private final ChatMessageService chatMessageService;

    @GetMapping("/api/chat/room/{uuid}/messages")
    public List<ChatMessageModel> getChatMessages(@PathVariable String uuid, @PageableDefault Pageable pageable) {
        return chatMessageService.geChatMessages(uuid, pageable);
    }
}
