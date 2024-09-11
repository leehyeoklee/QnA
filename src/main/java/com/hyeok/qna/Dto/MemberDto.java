package com.hyeok.qna.Dto;

import com.hyeok.qna.Role;
import lombok.Data;

@Data
public class MemberDto {
    private String username;
    private String password;
    private String role;
}
