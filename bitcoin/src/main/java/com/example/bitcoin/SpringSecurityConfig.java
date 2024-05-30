package com.example.bitcoin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig{


	@Bean
	PasswordEncoder passwordEncoder() {
	    return new SimplePasswordEncoder();
	}


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable()

		.authorizeHttpRequests(request -> request

				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				.requestMatchers("/rsi","/rsi14","/boardDetail2","/boardDetailAjax","/boardListAjax","/board2","/logout","/login","/static/**","/**.jpg","/**.png","/","/logout","/header.html","/footer.html","/market7","/currentPrice7","/chart2","/header2.html","/error").permitAll()
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
								.logoutUrl("/logout")
								.logoutSuccessUrl("/login")
								);


						return http.build();


	}
}
