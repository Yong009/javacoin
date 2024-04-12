package com.example.bitcoin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new SimplePasswordEncoder();
	}


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
                .authorizeHttpRequests(request -> request
                	.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                	.requestMatchers("/status","/images/","/join").permitAll()
                        .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
                )
                .formLogin(login -> login	// form 방식 로그인 사용
                		.loginPage("/login")
                		.loginProcessingUrl("/login-process")
                		.usernameParameter("userid")
                		.passwordParameter("pw")
                        .defaultSuccessUrl("/mainPage", true)	// 성공 시 dashboard로
                        .permitAll()	// 대시보드 이동이 막히면 안되므로 얘는 허용
                )
                .logout(logout -> logout
                		.logoutSuccessUrl("/logout")
                		.logoutSuccessUrl("/login")
                		.permitAll()
                		);	// 로그아웃은 기본설정으로 (/logout으로 인증해제)

        return http.build();
    }

}
