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
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

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
        member.setNickname(memberForm.getNickname());
        member.setPassword(passwordEncoder.encode(memberForm.getPassword()));
        member.setRole(Role.USER);

        return memberRepository.save(member);
    }

    //회원정보 조회
    public Member viewMember(String email) {
        return memberRepository.findByEmail(email);
    }

    //회원정보 중복 조회
    public void updateValid(MemberForm memberForm, Errors errors) {

        log.info("닉네임 = {}", memberRepository.findByEmail(memberForm.getEmail()).getNickname());
        log.info("닉네임 = {}", memberForm.getNickname());

        //닉네임 중복확인
        if(memberRepository.existsByNickname(memberForm.getNickname()) &&
                !(memberForm.getNickname().equals(memberRepository.findByEmail(memberForm.getEmail()).getNickname()))){
            errors.rejectValue("nickname", "invalid.nickname",
                    new Object[]{memberForm.getNickname()}, "이미 사용중인 닉네임 입니다.");
        }

        //비밀번호 중복확인
        if(!memberForm.getPassword().equals(memberForm.getPasswordCon())){
            errors.rejectValue("passwordCon", "invalid.passwordCon",
                    new Object[]{memberForm.getPasswordCon()}, "비밀번호와 일치하지 않습니다. 다시 확인해주세요.");
        }
    }

    //회원정보 수정
    public Member updateMember(MemberForm memberForm) {
        Member member = memberRepository.findByEmail(memberForm.getEmail());

        log.info("member = {}", member.getEmail());

        member.setNickname(memberForm.getNickname());
        member.setPassword(passwordEncoder.encode(memberForm.getPassword()));

        memberRepository.save(member);

        log.info(member.getEmail());
        log.info(member.getNickname());

        return member;
    }
}
