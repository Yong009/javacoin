package com.example.bitcoin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.example.bitcoin.dto.BoardVO;
import com.example.bitcoin.dto.MemberVO;


@Mapper
public interface coinmapper {

  //로그인
  public  MemberVO getUserById(String username);
  //코드 가져오기
  public List<MemberVO> getCode(String id);
  //코드 저장
  public boolean saveCode(MemberVO vo);
  // 게시판 리스트
  public List<BoardVO> getList();
  //회원가입
  public boolean join(MemberVO vo);
  //자동매매 끄기
  public void autoStop(String id);
  //자동매매 켜기
  public  void autoOn(String id);
  //자동매매 모니터링
  List<MemberVO> getMemberAuto(String auto);
  //회원정보 수정
  void updateMember(MemberVO vo);
  //전체 회원
  List<MemberVO> getMemberAll();
  //회원 탈퇴
  void deleteMember(String id);
  //회원가입
  void membereJoin(MemberVO member);
  //아이디 중복 체크
  int checkId(MemberVO member);
}
