package com.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//httpStatus.NOT_FOUND  ==>  404오류
//404오류에대한 사용자 정의 예외처리
@ResponseStatus(value= HttpStatus.NOT_FOUND ,reason = "게시글을 찾을 수 없습니다.")
public class DataNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public DataNotFoundException(String message) {
        super(message);
    }
}
