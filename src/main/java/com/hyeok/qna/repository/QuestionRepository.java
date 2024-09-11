package com.hyeok.qna.repository;

import com.hyeok.qna.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRepository extends JpaRepository<Question,Long> {
Question findQuestionById(Long id);
}
