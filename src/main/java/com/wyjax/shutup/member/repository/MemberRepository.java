package com.wyjax.shutup.member.repository;

import com.wyjax.shutup.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    void deleteById(Long id);
}
