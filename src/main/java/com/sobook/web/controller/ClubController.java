package com.sobook.web.controller;

import com.sobook.domain.club.Club;
import com.sobook.domain.club.ClubForm;
import com.sobook.domain.member.Member;
import com.sobook.service.ClubService;
import com.sobook.service.MemberService;
import com.sobook.web.validator.ClubValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/club")
public class ClubController {

    private final ClubService clubService;
    private final ModelMapper modelMapper;
    private final MemberService memberService;
    private final ClubValidator clubValidator;

    @InitBinder("clubForm")
    public void ClubFormInitBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(clubValidator);
    }

    @GetMapping("/new")
    public String newClubForm(Principal principal, Model model) {
        model.addAttribute(memberService.viewMember(principal.getName()));
        model.addAttribute(new ClubForm());
        return "club/clubForm";
    }

    @PostMapping("/new")
    public String newClub(Principal principal, @Valid ClubForm clubForm, BindingResult bindingResult) {

        Member member = memberService.viewMember(principal.getName());

        if(bindingResult.hasErrors()) {
            return "club/clubForm";
        }

        Club club = clubService.createNewClub(modelMapper.map(clubForm, Club.class), member);
        return "redirect:/club/" + URLEncoder.encode(club.getPath(), StandardCharsets.UTF_8);
    }

    @GetMapping("/{path}")
    public String viewClub(Principal principal, @PathVariable String path, Model model) {
        model.addAttribute("member", memberService.viewMember(principal.getName()));
        model.addAttribute(clubService.getClub(path));

        return "club/detail";
    }




}
