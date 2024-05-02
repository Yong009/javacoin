package com.example.bitcoin;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {



	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}





//	  public SpringSecurityConfig(@Lazy CustomAuthenticationProvider  customAuthenticationProvider)
//	  {
//		  this.customAuthenticationProvider =	  customAuthenticationProvider;
//	  }


	  @Bean
		protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		  http.csrf().disable().cors().disable()//일반 사용자에 대해 Session을 저장하지 않으므로 csrf을 disable 처리함.
				.authorizeRequests(request -> request
						 .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()

					     .anyRequest().authenticated()

					)
			.formLogin(login -> login
					/*.loginPage("/login")*/
					/*.loginProcessingUrl("/login-process")*/
					/*.usernameParameter("userid")*/
					/*.passwordParameter("pw")*/
					.defaultSuccessUrl("/mainPage", true) // 성공 시 dashboard로
					.permitAll() // 대시보드 이동이 막히면 안되므로 얘는 허용

			).logout(logout -> logout
					.logoutSuccessUrl("/login")
					.permitAll()

					);
			/*.logout(logout -> logout
				.logoutSuccessUrl("/logout")
				.logoutSuccessUrl("/login")
				.permitAll());*/


			  return http.build();
		}


	}




//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf().disable().cors().disable()
//				.authorizeHttpRequests(request -> request
//						.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//						.antMatchers("/board","/login","/join").permitAll()
//						/*.antMatchers("/mainPage").authenticated()*/
//						.anyRequest().authenticated()
//				// .anyRequest().authenticated() // 어떠한 요청이라도 인증필요
//				).formLogin(login -> login // form 방식 로그인 사용
//						.loginPage("/login")
//						.loginProcessingUrl("/login-process")
//						.usernameParameter("userid")
//						.passwordParameter("pw")
//						.defaultSuccessUrl("/mainPage", true) // 성공 시 dashboard로
//						.permitAll() // 대시보드 이동이 막히면 안되므로 얘는 허용
//				).logout(logout -> logout
//						.logoutSuccessUrl("/logout")
//						.logoutSuccessUrl("/login")
//						.permitAll()); // 로그아웃은
//																												// 기본설정으로
//																												// (/logout으로
//																												// 인증해제)
//
//		return http.build();
//	}


