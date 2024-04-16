package com.example.bitcoin.service;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;

import com.example.bitcoin.dto.MemberVO;



public interface coinservice {


	  //시큐리티 로그인
	  public  MemberVO findOne(String userId);


	  public List<MemberVO> getCode(String id);

	  public boolean saveCode(MemberVO vo);
}
