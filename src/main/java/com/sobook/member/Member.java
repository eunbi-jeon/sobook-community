package com.sobook.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String name;
    private String password;

    public static Member createMember(MemberForm memberForm){
        Member member = new Member();
        member.setEmail(memberForm.getEmail());
        member.setName(memberForm.getName());
        member.setPassword(memberForm.getPassword());
        return member;
    }
}
