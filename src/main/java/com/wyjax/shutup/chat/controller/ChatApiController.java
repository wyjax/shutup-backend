package com.wyjax.shutup.chat.controller;

import com.wyjax.shutup.chat.model.ChatRoomModel;
import com.wyjax.shutup.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ChatApiController {
    private static int nameIndex = 1;

    private final ChatRoomService chatRoomService;

    @GetMapping("/api/chat/rooms")
    public List<ChatRoomModel> getChatRooms() {
        return chatRoomService.getChatRooms();
    }

    @GetMapping("/api/chat/room/{roomId}")
    public ChatRoomModel getChatRoom(@PathVariable Long roomId) {
        return chatRoomService.getChatRoom(roomId);
    }

    @PostMapping("/api/chat/room")
    public ChatRoomModel addChatRoom(@RequestBody ChatRoomModel model) {
        return chatRoomService.addChatRoom(model);
    }

    @PutMapping("/api/chat/room/{roomId}")
    public ChatRoomModel updateChatRoom(@PathVariable Long roomId, @RequestBody ChatRoomModel model) {
        return chatRoomService.updateChatRoom(roomId, model);
    }

    @DeleteMapping("/api/chat/room/{roomId}")
    public ResponseEntity<Void> removeChatRoom(@PathVariable Long roomId) {
        chatRoomService.removeChatRoom(roomId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/chat/name")
    public String getRandomName() {
        synchronized (this) {
            return "채팅봇_" + nameIndex++;
        }
    }
}
