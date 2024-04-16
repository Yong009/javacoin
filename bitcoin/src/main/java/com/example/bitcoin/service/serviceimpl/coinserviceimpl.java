package com.example.bitcoin.service.serviceimpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.bitcoin.dto.MemberVO;
import com.example.bitcoin.mapper.coinmapper;
import com.example.bitcoin.service.coinservice;


@Service
@Primary
public class coinserviceimpl implements coinservice{

	@Autowired
	coinmapper coinmappers;

	@Override
	public MemberVO findOne(String userId) {

		return coinmappers.findById(userId);
	}

	@Override
	public List<MemberVO> getCode(String id) {
		return coinmappers.getCode(id);
	}

	@Override
	public boolean saveCode(MemberVO vo) {

		return coinmappers.saveCode(vo);
	}


}
