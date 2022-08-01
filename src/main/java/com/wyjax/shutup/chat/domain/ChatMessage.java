package com.wyjax.shutup.chat.domain;

import com.wyjax.shutup.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage extends BaseTimeEntity {

    @Column(length = 63, nullable = false)
    private String nickName;

    @Column(length = 63, nullable = false)
    private String loginId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @Builder
    public ChatMessage(ChatRoom chatRoom, String nickName, String loginId, String content) {
        this.chatRoom = chatRoom;
        this.nickName = nickName;
        this.loginId = loginId;
        this.content = content;
    }
}
