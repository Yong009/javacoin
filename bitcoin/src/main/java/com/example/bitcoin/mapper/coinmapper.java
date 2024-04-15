package com.example.bitcoin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.bitcoin.dto.MemberVO;

@Mapper
public interface coinmapper {

    //시큐리티 로그인
  public  MemberVO findById(String userId);

  public List<MemberVO> getCode(String id);
}
