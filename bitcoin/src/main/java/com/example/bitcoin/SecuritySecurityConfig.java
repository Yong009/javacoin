package com.example.bitcoin;

import org.springframework.boot.web.servlet.ServletContextInitializer;
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
public class SecuritySecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
	    return new SimplePasswordEncoder();
	}

	@Bean
	public ServletContextInitializer initializer() {
	    return servletContext -> {
	        servletContext.setSessionTimeout(30); // 세션 타임아웃을 30분으로 설정
	    };
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable()

		.authorizeHttpRequests(request -> request
				.requestMatchers("/static/**","/login","/**.jpg","/**.png","/","/logout","/header.html","/footer.html","/market7","/currentPrice7","/chart2","/header2.html").permitAll()
				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				.anyRequest().authenticated()


				).formLogin(login -> login
						.loginPage("/login")
						.permitAll()
						.loginProcessingUrl("/login-process")
						.usernameParameter("userid")
                        .passwordParameter("pw")
						.defaultSuccessUrl("/mainPage",true)
						.permitAll()

						).logout(logout -> logout
								.logoutSuccessUrl("/")
								);


						return http.build();


	}
}
