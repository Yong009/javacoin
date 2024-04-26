package com.example.bitcoin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.bitcoin.dto.MemberVO;
import com.example.bitcoin.mapper.coinmapper;


@Component
public class MyUserDetailService implements UserDetailsService{

	@Autowired
	coinmapper coinservice3;

	@Autowired
	PasswordEncoder passwordEncoder;







	public MyUserDetailService(coinmapper coinservice3) {
		this.coinservice3 = coinservice3;
	}

	@Override
	public UserDetails loadUserByUsername(String insertedUserId) throws UsernameNotFoundException{
		//MemberVO member = Optional.ofNullable(coinservice3.findById(insertedUserId))
		//		.orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다. ㅠ"));
		MemberVO member = coinservice3.findById(insertedUserId);

		if(member == null) {
			throw new UsernameNotFoundException("username" + insertedUserId + "not found");

		}
		System.out.println("***************Found user***************");
		System.out.println("id : "+ member.getId());





	    // role이 'Y'가 아닌 경우 로그인 거부
	   /* if(!"Y".equals(member.getRole())) {
	        throw new UsernameNotFoundException("로그인할 수 없는 회원입니다.");
	    }*/

//		UserDetails userDetails = User.builder()
//				.username(member.getId())
//				.password(member.getPassword())
//				.roles(member.getRole())
//				.build();

//		return userDetails;
	//	return new org.springframework.security.core.userdetails.User(member.getId(), member.getPassword(), null);
		return User.builder()
				.username(member.getId())
				.password(member.getPassword())
				.roles(member.getRole())
				.build();
	}
}
