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
	//매수
	String order7(OrderVO vo) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	//매도
	String sell7(OrderVO vo) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	//자동매매 끄기
	public void autoStop7(String id);
	//자동매매 켜기
	void autoOn(String id);
	//자동매매 모니터링
	List<MemberVO> getMemberAuto(String auto);
	//회원 정보 수정
	void updateMember(MemberVO vo);
	// 전체 멤버
	List<MemberVO> getMemberAll();
	//회원탈퇴
	void deleteMember(String id);
	//회원가입
	void memberJoin(MemberVO member);
	//아디 중복 체크
	int checkId(MemberVO member);

	/*public void join(MemberVO vo);*/


}
