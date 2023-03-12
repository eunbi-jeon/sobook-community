package com.sobook.service;

import com.sobook.domain.constant.Role;
import com.sobook.domain.member.Member;
import com.sobook.domain.member.MemberForm;
import com.sobook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //회원 가입
    public Member saveMember(MemberForm memberForm) {

        Member member = new Member();
        member.setEmail(memberForm.getEmail());
        member.setName(memberForm.getName());
        member.setPassword(passwordEncoder.encode(memberForm.getPassword()));
        member.setRole(Role.USER);

        return memberRepository.save(member);
    }

}
