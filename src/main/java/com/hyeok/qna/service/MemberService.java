package com.hyeok.qna.service;

import com.hyeok.qna.Dto.MemberDto;
import com.hyeok.qna.domain.Member;
import com.hyeok.qna.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public Member saveMember(Member member){
        validateDuplicatedMember(member);
        return memberRepository.save(member);
    }

    public void validateDuplicatedMember(Member member){
        Member savedMember = memberRepository.findMemberByUsername(member.getUsername());
        if (savedMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
