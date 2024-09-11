package com.hyeok.qna.service;

import com.hyeok.qna.CustomUserDetails;
import com.hyeok.qna.domain.Member;
import com.hyeok.qna.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findMemberByUsername(username);
        if (member!=null){
            return new CustomUserDetails(member);
        }
        return null;
    }
}
