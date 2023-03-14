package com.sobook.domain.member;

import com.sobook.domain.constant.Role;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id") //컴파일 시점 자동으로 equal과 hashcode 메소드를 오버라이딩
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;
    private String password;

    private LocalDateTime joinedAt;

    @Lob @Basic(fetch = FetchType.EAGER)
    private String profileImage;

    @Enumerated(EnumType.STRING)
    private Role role;

}
