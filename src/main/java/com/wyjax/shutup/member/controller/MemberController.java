package com.wyjax.shutup.member.controller;

import com.wyjax.shutup.member.model.MemberJoinModel;
import com.wyjax.shutup.member.model.MemberModel;
import com.wyjax.shutup.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/api/member")
    public MemberModel join(@RequestBody MemberJoinModel model) {
        return memberService.join(model);
    }
}
