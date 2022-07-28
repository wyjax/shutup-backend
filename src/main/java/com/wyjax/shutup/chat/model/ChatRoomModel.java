package com.wyjax.shutup.chat.model;

import com.wyjax.shutup.chat.domain.ChatRoom;
import com.wyjax.shutup.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoomModel {
    private String name;
    private String uuid;
    private List<String> members;

    public ChatRoomModel(ChatRoom chatRoom) {
        this.name = chatRoom.getName();
        this.uuid = chatRoom.getUuid();
        if (chatRoom.getMembers() != null) {
            this.members = chatRoom.getMembers().stream()
                    .map(Member::getName)
                    .collect(Collectors.toList());
        }
    }
}
