package com.example.bitcoin.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;



import com.example.bitcoin.dto.BoardVO;
import com.example.bitcoin.dto.MemberVO;
import com.example.bitcoin.dto.OrderVO;


public interface coinservice {


	//시큐리티 로그인
	public  boolean findOne(String userId, String pw);
	//코드 가져오기
	public List<MemberVO> getCode(String id);
	//코드 저장
	public boolean saveCode(MemberVO vo);
	//게시판 전체 리스트
	public List<BoardVO> getList();
	//잔고 조회
    String account7(MemberVO vo);
	//현재가격 조회
	String currentPrice7();
	//마켓 정보
	String market7();

	String order7(OrderVO vo) throws NoSuchAlgorithmException, UnsupportedEncodingException;

	String sell7(OrderVO vo) throws NoSuchAlgorithmException, UnsupportedEncodingException;

	//자동매매 끄기
	public void autoStop7(String id);

	/*public void join(MemberVO vo);*/


}
