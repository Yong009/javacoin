package com.example.bitcoin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.bitcoin.dto.MemberVO;
import com.example.bitcoin.mapper.coinmapper;

@Component
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	coinmapper coinmapper7;

	public MyUserDetailsService(coinmapper coinmapper7) {
		this.coinmapper7 = coinmapper7;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		MemberVO member = coinmapper7.getUserById(username);

		if (member == null) {
            throw new UsernameNotFoundException(username);
        }

		return User.builder()
				.username(member.getId())
				.password(member.getPassword())
				.roles(member.getRole())
				.build();
	}



}
