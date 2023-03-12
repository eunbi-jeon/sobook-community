package com.sobook.member;

import com.sobook.domain.member.Member;
import com.sobook.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MemberTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void configure() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String result = encoder.encode("12345");
        System.out.println("result = " + result);
        assertTrue(encoder.matches("12345", result));
    }
    //회원 가입
    @Test
    public void save() {
        Member member = new Member();
        member.setName("테스트");
        member.setPassword("test!");
        member.setEmail("test@test.com");

        memberRepository.save(member);

        Member result = memberRepository.findById(member.getId()).get();
        assertThat(member.getName()).isEqualTo(result.getName());
    }

}
