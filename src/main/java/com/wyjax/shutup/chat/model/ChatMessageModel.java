package com.wyjax.shutup.chat.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessageModel {
    private MessageType type;
    private String content;
    private String name;
    private String uuid;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}
