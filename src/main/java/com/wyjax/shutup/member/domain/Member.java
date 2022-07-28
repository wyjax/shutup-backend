package com.wyjax.shutup.member.domain;

import com.wyjax.shutup.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Column(length = 31, unique = true)
    private String name;

    @Column(length = 31, unique = true)
    private String username;

    @Column(length = 63, nullable = false)
    private String password;

    @Builder
    public Member(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
