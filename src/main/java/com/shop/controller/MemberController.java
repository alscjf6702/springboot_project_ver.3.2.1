package com.shop.controller;

import com.shop.service.MemberService;
import com.shop.validataion.MemberRegForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signup(MemberRegForm memberRegForm) {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberRegForm memberRegForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";   //유효성 검증에 에러가 나타나면 signup_form페이지로 돌아가라
        }

        if (!memberRegForm.getPassword1().equals(memberRegForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 비밀번호가 일치하지 않습니다.");
            return "signup_form";   //password1과 password2가 같은지 비교하여 같지않다면 비밀번호가 일치하지 않다는 메세지 출력
        }

        memberService.create(memberRegForm.getUsername(),  memberRegForm.getEmail(), memberRegForm.getPassword1());
        //memberService의 create 메서드의 매개변수 순서대로 맞춰줘야 함

        return "redirect:/";


    }
}
