package com.hyeok.qna.controller;

import com.hyeok.qna.domain.Question;
import com.hyeok.qna.repository.QuestionRepository;
import com.hyeok.qna.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionRepository questionRepository;
    private final QuestionService questionService;

    @GetMapping("/form")
    String questionForm() {
        return "question_form";
    }

    @PostMapping("/new")
    String saveQuestion(Question question) {
        questionRepository.save(question);
        return "thank_you";
    }

    @GetMapping("/list")
    String list(Model model) {
        List<Question> questions = questionService.findMembers();
        model.addAttribute("questions", questions);
        return "question_list";
    }

    @GetMapping("/answer/{id}")
    String answerForm(@PathVariable("id") Long id, Model model) {
        Question question = questionRepository.findQuestionById(id);
        model.addAttribute(question);
        return "answer_form";
    }

    @PostMapping("/answer/{id}")
    String saveAnswer(String answer, @PathVariable("id") Long id) {
        Question question = questionRepository.findQuestionById(id);
        question.setAnswer(answer);
        questionRepository.save(question);
        return "redirect:/question/list";
    }
    @GetMapping("/all")
    String answerForm(Model model) {
        List<Question> questions = questionService.findMembers();
        model.addAttribute("questions",questions);
        return "all_contents";
    }
}
