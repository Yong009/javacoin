package com.example.bitcoin.service;

import java.util.List;



import com.example.bitcoin.dto.BoardVO;
import com.example.bitcoin.dto.MemberVO;



public interface coinservice {


	//시큐리티 로그인
	public  boolean findOne(String userId, String pw);
	//코드 가져오기
	public List<MemberVO> getCode(String id);
	//코드 저장
	public boolean saveCode(MemberVO vo);
	//게시판 전체 리스트
	public List<BoardVO> getList();

	/*public void join(MemberVO vo);*/


}
