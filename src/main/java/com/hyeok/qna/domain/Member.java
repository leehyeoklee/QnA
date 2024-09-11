package com.hyeok.qna.domain;

import com.hyeok.qna.Dto.MemberDto;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column
    private String role;

    public static Member create(MemberDto memberDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setUsername(memberDto.getUsername());
        member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        member.setRole(memberDto.getRole());
        return member;
    }
}
