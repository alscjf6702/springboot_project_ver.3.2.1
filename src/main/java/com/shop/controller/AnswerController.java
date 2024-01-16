package com.shop.controller;

import com.shop.entity.Question;
import com.shop.service.AnswerService;
import com.shop.service.QuestionService;
import com.shop.validataion.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;


    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm , BindingResult bindingResult) {
        Question question = questionService.getQuestion(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("one", question);
            return "questionDetail";
        }
        answerService.registerAnswer(question, answerForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }



}
