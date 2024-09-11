package com.hyeok.qna.service;

import com.hyeok.qna.domain.Question;
import com.hyeok.qna.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    public List<Question> findMembers() {
        return questionRepository.findAll();
    }

}
