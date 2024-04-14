package com.example.bitcoin.service;

import org.springframework.context.annotation.Primary;

import com.example.bitcoin.dto.MemberVO;



public interface coinservice {


	  //시큐리티 로그인
	  public  MemberVO findOne(String userId);


    public MemberVO codeSave(MemberVO vo);
}
