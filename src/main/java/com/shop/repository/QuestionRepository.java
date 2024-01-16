package com.shop.repository;

import com.shop.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findBySubject(String subject);

    Question findBySubjectAndId(String subject, Integer id);

    List<Question> findBySubjectLike(String subject);

    Page<Question> findAll(Pageable pageable);


}
