package com.hyeok.qna.repository;

import com.hyeok.qna.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findMemberByUsername(String username);

}
