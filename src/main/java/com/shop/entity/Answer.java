package com.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter

public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;


    //게시판에는 하나의 질문에 답변이 여러개 달릴 수 있다.
    //이 애너테이션을 설정하면 Answer 엔티티의 question속성과 Question엔티티가 서로 외래키 관계로 연결 된다. (FK)
    @ManyToOne
    private Question question;


}
