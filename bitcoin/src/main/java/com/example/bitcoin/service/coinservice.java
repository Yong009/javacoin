package com.example.bitcoin.service;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;

import com.example.bitcoin.dto.BoardVO;
import com.example.bitcoin.dto.MemberVO;



public interface coinservice {


	//시큐리티 로그인
	public  MemberVO findOne(String userId);
	//코드 가져오기
	public List<MemberVO> getCode(String id);
	//코드 저장
	public boolean saveCode(MemberVO vo);
	//게시판 전체 리스트
	public List<BoardVO> getList();
	//회원가입
	public boolean join(MemberVO vo);
}
