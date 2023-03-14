package com.sobook.domain.club;

import com.sobook.domain.member.Member;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

// EAGER FETCH 할 수 있도록
@NamedEntityGraph(name = "Club.withAll", attributeNodes = {
        @NamedAttributeNode("members"),
})
@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @NoArgsConstructor @AllArgsConstructor
public class Club {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String path;
    private String title;
    private String shortDescription;

    @Lob @Basic(fetch = FetchType.EAGER) @Type(type = "org.hibernate.type.TextType")
    private String images;

    @ManyToMany
    private Set<Member> managers = new HashSet<>(); //매니저 여러명

    @ManyToMany
    private Set<Member> members = new HashSet<>(); //멤버 여러명

    private LocalDateTime createTime; //모임 등록일
    private LocalDateTime closeDateTime; //모임 모집 마감일
    private LocalDateTime recruitingUpdatedTime; //모임 재 모집 등록일

    private boolean Recruiting; //모집중
    private boolean isClosed; //마감

    //매니저 추가
    public void addManager(Member member) {
        this.managers.add(member);
    }

    //가입여부 확인 관련 메소드
    public boolean isJoinable(@AuthenticationPrincipal Member member) {
        return this.isRecruiting() &&!this.members.contains(member)
                && !this.managers.contains(member);
    }

    public boolean isMember(@AuthenticationPrincipal Member member) {
        return this.members.contains(member);
    }

    public boolean isManager(@AuthenticationPrincipal Member member) {
        return this.managers.contains(member);
    }
}
