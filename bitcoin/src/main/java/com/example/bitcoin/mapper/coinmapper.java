package com.example.bitcoin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.bitcoin.dto.MemberVO;

@Mapper
public interface coinmapper {

    //시큐리티 로그인
  public  MemberVO findById(String userId);

  public MemberVO codeSave(MemberVO vo);
}
