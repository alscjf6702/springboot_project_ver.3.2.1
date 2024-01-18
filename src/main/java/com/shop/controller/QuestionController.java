package com.shop.controller;

import com.shop.entity.Question;
import com.shop.service.QuestionService;
import com.shop.validataion.AnswerForm;
import com.shop.validataion.QuestionForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/question/list")
    public String list(Model model,@RequestParam(value="page",defaultValue = "1") int page){
//        List<Question> questionList = questionService.getList();
        Page<Question> questionList = questionService.getList(page);
        model.addAttribute("list", questionList);
        return "questionList";
    }

    @GetMapping("/question/detail/{id}")
    public String detail(Model model, @PathVariable("id")Integer id, AnswerForm answerForm){
        model.addAttribute("one", questionService.detail(id));
        return "questionDetail";
    }

    @GetMapping("/question/register")
    public String registerGet(QuestionForm questionForm){
        return "questionRegister";
    }

    @PostMapping("/question/register")
    public String registerPost(@Valid QuestionForm questionForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "questionRegister";
        }
        this.questionService.register(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";
    }

    @PostMapping("/question/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        questionService.delete(id);
        return "redirect:/question/list";
    }

    @GetMapping("/")    //root URL localhost:8080으로 접속했을 때 list화면으로 redirect해준다
    public String root(){
        return "redirect:/question/list";
    }
}
