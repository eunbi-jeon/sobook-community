package com.sobook.domain.member;

import com.sobook.repository.MemberRepository;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class MemberAccount extends User {
    private Member member;

    public MemberAccount(Member member) {
        super(member.getNickname(),
                member.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));

        this.member = member;
    }

}
