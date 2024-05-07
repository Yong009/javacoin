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


}
