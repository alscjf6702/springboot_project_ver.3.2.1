package com.shop;

import com.shop.entity.Answer;
import com.shop.entity.Question;
import com.shop.repository.AnswerRepository;
import com.shop.repository.QuestionRepository;
import com.shop.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ShopApplicationTests {

	@Autowired	//객체를 자동으로 만들어 주입시켜줌 테스트를 할 경우에는 객체 주입이 안되니 autowired를 사용함
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionService questionService;

	@Test
	public void testJpa(){

		for (int i = 1; i <= 300; i++) {
			String subject = String.format("테스트 제목:[%03d]", i);
			String content= "테스트 내용";
			questionService.register(subject,content);
		}

	}

}
