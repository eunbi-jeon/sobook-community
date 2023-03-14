package com.sobook.service;

import com.sobook.domain.member.Member;
import com.sobook.domain.member.MemberForm;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    public Member updateMember(MemberForm memberForm) {
        Member member = memberRepository.findByEmail(memberForm.getEmail());

        member.setNickname(passwordEncoder.encode(memberForm.getNickname()));
        member.setPassword(passwordEncoder.encode(memberForm.getPassword()));

        return memberRepository.save(member);
    }



}