package com.wyjax.shutup.chat.domain;

import com.wyjax.shutup.chat.model.ChatRoomModel;
import com.wyjax.shutup.chat.type.MessageType;
import com.wyjax.shutup.common.domain.BaseTimeEntity;
import com.wyjax.shutup.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseTimeEntity {

    @Column(length = 63, nullable = false)
    private String name;

    @Column(unique = true, updatable = false)
    private String uuid;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<Member> members;

    @Builder
    public ChatRoom(String name, String uuid, MessageType type) {
        this.name = name;
        this.uuid = uuid;
        this.type = type;
    }

    public void updateName(String name) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalStateException("ChatRoom 이름은 유효한 값이 있어야 합니다.");
        }
        this.name = name;
    }

    public void addMember(Member member) {
        if (this.members == null) {
            this.members = new HashSet<>();
        }
        this.members.add(member);
    }

    public void update(ChatRoomModel model) {
        this.name = model.getName();
    }
}
