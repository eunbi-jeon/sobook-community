package com.sobook.domain.club;

import com.sobook.domain.constant.ClubMemberRole;
import com.sobook.domain.member.Member;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class ClubMember {

    /**
     * 모임 가입 회원을 따로 정리
     */

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private ClubMemberRole role;

    @ManyToOne //여러 계정은 하나의 모임에 속할 수 있음
    @JoinColumn(name = "CLUB_ID")
    private Club club;

    @ManyToOne //여러 모임은 하나의 계정에 속할 수 있음
    @JoinColumn(name = "MEMBER_ID")
    private Member member;


    public ClubMember(ClubMemberRole role, Club club, Member member) {
        this.role = role;
        this.club = club;
        this.member = member;
    }
}
