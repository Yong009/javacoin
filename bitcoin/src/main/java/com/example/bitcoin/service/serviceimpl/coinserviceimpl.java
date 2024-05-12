package com.example.bitcoin.service.serviceimpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;


import org.springframework.stereotype.Service;

import com.example.bitcoin.dto.BoardVO;
import com.example.bitcoin.dto.MemberVO;
import com.example.bitcoin.mapper.coinmapper;
import com.example.bitcoin.service.coinservice;


@Service
@Primary
public class coinserviceimpl implements coinservice {

    @Autowired
    coinmapper coinmappers;


    //PasswordEncoder passwordEncoder;

    //회원가입
    /*@Override
    public void join(MemberVO vo) {

        MemberVO member = MemberVO.createUser(vo.getId(), vo.getPassword(), passwordEncoder);
        coinmappers.join(member);


    }*/

    //로그인
    /*@Override
    public boolean findOne(String userId, String pw) {
        MemberVO member = coinmappers.getUserDetails(userId);

        if(bCryptPasswordEncoder.matches(pw, member.getPassword())){
            return true; // 입력한 비밀번호와 저장소의 비밀번호가 일치
        } else {
            return false;
        }

    }*/

    @Override
    public boolean findOne(String userId, String pw) {
        return false;
    }

    //코드 가져오기
    @Override
    public List<MemberVO> getCode(String id) {
        return coinmappers.getCode(id);
    }

    //코드 저장
    @Override
    public boolean saveCode(MemberVO vo) {

        return coinmappers.saveCode(vo);
    }

    //게시판 전체 리스트
    @Override
    public List<BoardVO> getList() {

        return coinmappers.getList();
    }


}
