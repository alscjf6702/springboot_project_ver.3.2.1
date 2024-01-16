package com.shop.service;

import com.shop.entity.Question;
import com.shop.exception.DataNotFoundException;
import com.shop.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    //페이징 처리 없이 목록 불러오기
    public List<Question> getList(){
        return questionRepository.findAll();
    }

    //페이징 처리해서 목록 불러오기
    public Page<Question> getList(int page){
        List<Sort.Order> sorts= new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10,Sort.by(sorts));

        return questionRepository.findAll(pageable);
    }

    public Question detail(@Param("id")Integer id){
        Optional<Question> detail = questionRepository.findById(id);

        if (detail.isPresent()) {
            return detail.get();
        }else{
            throw new DataNotFoundException("질문글이 없습니다.");
        }

    }

    public Question getQuestion(Integer id) {
        Optional<Question> id1 = questionRepository.findById(id);
        Question question = id1.get();
        return question;
    }

    public void register(String subject, String content){
        Question question = new Question();
        question.setCreateDate(LocalDateTime.now());
        question.setSubject(subject);
        question.setContent(content);
        questionRepository.save(question);
    }

    public void delete(Integer id) {
        Optional<Question> saved = questionRepository.findById(id);

        Question question = saved.get();
        questionRepository.delete(question);

    }


}
