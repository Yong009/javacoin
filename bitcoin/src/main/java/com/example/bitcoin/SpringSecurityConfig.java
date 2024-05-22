package com.example.bitcoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig{

	private static final Logger logger = LoggerFactory.getLogger(SpringSecurityConfig.class);

	@Bean
	PasswordEncoder passwordEncoder() {
	    return new SimplePasswordEncoder();
	}


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable()

		.authorizeHttpRequests(request -> request

				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				.requestMatchers("/logout","/loginPage","/login","/static/**","/**.jpg","/**.png","/","/logout","/header.html","/footer.html","/market7","/currentPrice7","/chart2","/header2.html","/error").permitAll()
				 .anyRequest().authenticated()


				).formLogin(login -> login
						.loginPage("/loginPage")
						.permitAll()
						.loginProcessingUrl("/login")
						.usernameParameter("userid")
                        .passwordParameter("pw")
						.defaultSuccessUrl("/mainPage",true)
						.permitAll()

						).logout(logout -> logout

								.logoutSuccessUrl("/loginPage")
								);


		logger.debug("Security configuration loaded successfully.");
						return http.build();


	}
}
