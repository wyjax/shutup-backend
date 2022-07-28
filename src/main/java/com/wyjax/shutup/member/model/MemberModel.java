package com.wyjax.shutup.member.model;

import com.wyjax.shutup.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberModel {
    private String name;
    private String username;

    public MemberModel(Member member) {
        this.name = member.getName();
        this.username = member.getUsername();
    }
}
