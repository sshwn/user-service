package com.example.userservice.security;

import com.example.userservice.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Environment env;

    public WebSecurity(Environment env, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.env = env;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {  // 권한
        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/users/**").permitAll();  // /users/** 로 호출되는 uri 값은 통과한다는 의미
        http.authorizeRequests().antMatchers("/**")// 인증이 된 상태에서만 추가적으로 사용자 목록, 주문내역 확인 등등 가능하도록 처리
                .hasIpAddress("192.168.0.6")
                .and()
                .addFilter(getAuthenticationFilter());

        http.headers().frameOptions().disable(); // 추가하지 않으면 h2-console 접근 안됨
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter =
                new AuthenticationFilter(authenticationManager(), userService, env);
//        authenticationFilter.setAuthenticationManager(authenticationManager()); // spring Security 기능 이용 -> 생성자로 대체

        return authenticationFilter;
    }

    // 사용자가 전달한 id pw로 로그인 처리를 하는데 처리함에 있어 사용자 데이터를 검색 해온다 getUser 와 같은 식으로 select pwd from users where email=?
    // db_pwd(encrypted) == input_pwd(encrypted)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {  // 인증
        auth.userDetailsService(userService) // select pwd from users where email=?
                .passwordEncoder(bCryptPasswordEncoder);    // db_pwd(encrypted) == input_pwd(encrypted)

    }
}
