package com.sobook.web.controller;

import com.sobook.domain.member.Member;
import com.sobook.domain.member.MemberForm;
import com.sobook.service.MemberService;
import com.sobook.web.validator.MemberFormValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberFormValidator memberFormValidator;

    /* 회원 가입 */
    @GetMapping("/new")
    public String memberForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "member/memberForm";
    }

    @PostMapping("/new")
    public String newMember(@Valid @ModelAttribute MemberForm memberForm,
                            BindingResult bindingResult) {

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

    /* 회원 정보 페이지 */
    @PreAuthorize("isAuthenticated()") //로그인한 사용자만 정보를 조회할 수 있도록 함
    @GetMapping("/profile/{nickname}")
    public String memberProfile(@PathVariable String nickname,
                                Model model,
                                Principal principal) {

        String loginId = principal.getName();
        log.info("현재사용자 = {}", loginId);
        Member member = memberService.viewMember(loginId);

        model.addAttribute("member", member);
        return "member/memberDetail";
    }

    /* 회원 정보 수정 */
    @GetMapping("/update")
    public String memberUpdate(@AuthenticationPrincipal MemberForm memberForm, Model model) {
        log.info("사용자 = {}", memberForm.getEmail());
        model.addAttribute("memberForm", memberForm);
        return "member/memberUpdateForm";
    }





}
