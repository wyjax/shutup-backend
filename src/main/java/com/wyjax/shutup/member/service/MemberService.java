package com.wyjax.shutup.member.service;

import com.wyjax.shutup.member.domain.Member;
import com.wyjax.shutup.member.model.MemberJoinModel;
import com.wyjax.shutup.member.model.MemberModel;
import com.wyjax.shutup.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberModel join(MemberJoinModel model) {
        Member member = Member.builder()
                .name(model.getName())
                .username(model.getUsername())
                .password(model.getPassword())
                .build();
        member = memberRepository.save(member);
        return new MemberModel(member);
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
