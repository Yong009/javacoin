package com.example.bitcoin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.bitcoin.dto.MemberVO;
import com.example.bitcoin.mapper.coinmapper;
import com.example.bitcoin.service.coinservice;
import org.springframework.web.bind.annotation.ModelAttribute;


@Component
public class MyUserDetailService implements UserDetailsService{

	@Autowired
	coinmapper coinservice3;


	public MyUserDetailService(coinmapper coinservice3) {
		this.coinservice3 = coinservice3;
	}

	@Override
	public UserDetails loadUserByUsername(String insertedUserId) throws UsernameNotFoundException{


		MemberVO member = Optional.ofNullable(coinservice3.findById(insertedUserId))
				.orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다. ㅠ"));

		return User.builder()
				.username(member.getId())
				.password(member.getPassword())
				.roles(member.getRole())
				.build();
	}
}
