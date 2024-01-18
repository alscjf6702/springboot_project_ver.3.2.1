package com.shop.validataion;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRegForm {

    @Size(min = 3, max = 25)    //입력받은 데이터의 길이가 3~25
    @NotEmpty(message = "ID를 입력해주세요.")
    private String username;

    @NotEmpty(message = "이메일을 입력해주세요.")
    @Email                      //@email은 해당 입력값이 이메일 형식과 맞는지 확인함
    private String email;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인을 입력해주세요.")
    private String password2;

}
