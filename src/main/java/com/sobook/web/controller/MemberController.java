package com.sobook.web.controller;

import com.sobook.domain.member.MemberForm;
import com.sobook.service.MemberService;
import com.sobook.web.validator.MemberFormValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberFormValidator memberFormValidator;

    /* 회원 가입 */
    @GetMapping("/new")
    public String memberForm(@ModelAttribute("memberForm") MemberForm memberForm) {
        return "member/memberForm";
    }

    @PostMapping("/new")
    public String newMember(@Valid @ModelAttribute MemberForm memberForm,
                            BindingResult bindingResult,
                            Model model) {

        //중복검사
        memberFormValidator.validate(memberForm, bindingResult);

        if(bindingResult.hasErrors()){
            return "member/memberForm";
        }

        memberService.saveMember(memberForm);

        return "redirect:/";
    }

    /* 회원 로그인 */
    @GetMapping("/login")
    public String loginMember() {
        return "member/memberLoginForm";
    }

    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "member/memberLoginForm";
    }

}
