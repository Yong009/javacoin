//package com.example.bitcoin;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.example.bitcoin.dto.MemberVO;
//import com.example.bitcoin.mapper.coinmapper;
//
//public class MyUserDetailsService implements UserDetailsService{
//
//	@Autowired
//	coinmapper coinmapper5;
//
//	public MyUserDetailsService(coinmapper coinmapper5) {
//		this.coinmapper5 = coinmapper5;
//
//
//	}
//
//
//	@Override
//	public UserDetails loadUserByUsername(String insertedUserid) throws UsernameNotFoundException {
//
//	    MemberVO member2 = (MemberVO) coinmapper5.getUserDetails(insertedUserid);
//
//	    if (member2 == null) {
//	    	throw new UsernameNotFoundException("유효하지 않는 로그인 정보입니다.");
//	    }
//
//		return User.builder()
//				.username(member2.getId())
//				.password(member2.getPassword())
//				.roles(member2.getRole())
//				.build();
//
//	}
//
//}
