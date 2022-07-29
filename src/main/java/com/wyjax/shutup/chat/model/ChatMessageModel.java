package com.wyjax.shutup.chat.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatMessageModel {
    private MessageType type;
    private String content;
    private String sender;
    private Long roomId;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}
