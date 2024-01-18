package com.shop.service;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //Bean에 등록한것을 가져온다.
    private final PasswordEncoder passwordEncoder;
    public Member create(String username, String email, String password){
        Member member = new Member();
        member.setUsername(username);
        member.setEmail(email);
        //BCrypt 해시 함수를 사용하여 보안정보를 안전하게 저장하고 검증할 때 사용하는 암호화 기술
        //이렇게 객체를 직접 new 로 생성하는 방식보다는 passwordEncoder객체를 빈으로 등록해서 사용하는것을 권장한다.
        //암호화 방식을 변경하면 BcryptPasswordEncoder를 사용한 모든 프로그램을 찾아서 수정해야하기 떄문이다.
        //밑의 두 줄을 지우고 SecurityConfig에 @Bean어너테이션을 사용해서 메서드를 추가해준다.
//        BCryptPasswordEncoder PasswordEncoder = new BCryptPasswordEncoder();
//        member.setPassword(PasswordEncoder.encode(password));

        //SecurityConfig에 등록해준 후 객체 생성없이 바로 써주면 된다.
        member.setPassword(passwordEncoder.encode(password));


        memberRepository.save(member);
        return member;
    }

}
