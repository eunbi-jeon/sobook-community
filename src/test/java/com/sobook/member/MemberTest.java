package com.sobook.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MemberTest {

    @Autowired
    private MemberRepository memberRepository;
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
