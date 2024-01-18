package com.shop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                // 모든 경로("/**")를 허용한다.
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
                //h2콘솔은 토큰발행기능이 없어서 csrf토큰을 발행할 수 없어서 403오류가 뜨니 csrf예외처리 해준다.
                .csrf((csrf)->csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
                //csrf예외 처리 후 H2콘솔 레이아웃에 오류가 발생한다. 이 부분은 X-Frame-Options 헤더의 기본값이 DENY로 설정되어 있는데,
                //프레임 구조의 웹 사이트는 headers의 값이 DENY인 경우 오류가 발생하게 된다.
                //headers의 값을 SAMEORIGIN으로 변경을 해줘야 한다.
                //X-Frame_options 헤더는 클릭재킹 공격을 막기 위해 사용함
                      .headers((headers)->headers
                                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)));

        return http.build();
    }

    //Service단에 객체를 생성해주는 것보다 이렇게 securityConfig에 bean을 등록해서 사용해주는것을 권장함
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
