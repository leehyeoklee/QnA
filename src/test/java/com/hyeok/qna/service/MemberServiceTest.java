package com.hyeok.qna.service;

import com.hyeok.qna.Dto.MemberDto;
import com.hyeok.qna.Role;
import com.hyeok.qna.domain.Member;
import com.hyeok.qna.repository.MemberRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    Member make(){
        MemberDto memberDto = new MemberDto();
        memberDto.setUsername("admin");
        memberDto.setPassword("1234");
        memberDto.setRole(Role.ADMIN.name());
        return Member.create(memberDto,passwordEncoder);
    }
    @Commit
    @Test
    void 회원가입(){
        Member member1 = make();
        Member member2 = memberService.saveMember(member1);
        assertEquals(member1.getUsername(),member2.getUsername());
        assertEquals(member1.getPassword(),member2.getPassword());
        assertEquals(member1.getRole(),member2.getRole());
    }

    @Test
    void 중복회원가입(){
        Member member1 = make();
        Member member2 = make();
        memberService.saveMember(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.saveMember(member2));
        assertEquals(e.getMessage(),"이미 가입된 회원입니다.");
    }
}