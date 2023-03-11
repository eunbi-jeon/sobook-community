package com.sobook.member;

import com.sobook.web.validator.MemberFormValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.PresentationDirection;
import javax.validation.Valid;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberFormValidator memberFormValidator;
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

        log.info("memberForm {}", memberForm.getEmail());

        Member member = Member.createMember(memberForm);

        log.info("member = {}", member.getEmail());

        memberService.saveMember(member);

        return "redirect:/";
    }
}
