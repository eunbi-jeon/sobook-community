package com.sobook.web.validator;

import com.sobook.domain.member.MemberForm;
import com.sobook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class MemberFormValidator implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(MemberForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberForm memberForm = (MemberForm) target;

        //아이디 중복확인
        if(memberRepository.existsByEmail(memberForm.getEmail())){
            errors.rejectValue("email", "invalid.email",
                    new Object[]{memberForm.getEmail()}, "이미 사용중인 이메일 입니다.");
        }

        //닉네임 중복확인
        if(memberRepository.existsByNickname(memberForm.getNickname())){
            errors.rejectValue("nickname", "invalid.nickname",
                    new Object[]{memberForm.getNickname()}, "이미 사용중인 닉네임 입니다.");
        }

        //비밀번호 중복확인
        if(!memberForm.getPassword().equals(memberForm.getPasswordCon())){
            errors.rejectValue("passwordCon", "invalid.passwordCon",
                    new Object[]{memberForm.getPasswordCon()}, "비밀번호와 일치하지 않습니다. 다시 확인해주세요.");
        }
    }


}
