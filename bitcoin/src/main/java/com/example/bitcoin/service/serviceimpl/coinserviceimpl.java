package com.example.bitcoin.service.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.bitcoin.dto.MemberVO;
import com.example.bitcoin.mapper.coinmapper;
import com.example.bitcoin.service.coinservice;



public class coinserviceimpl implements coinservice{

	@Autowired
	coinmapper coinmappers;

	@Override
	public MemberVO findOne(String userId) {

		return coinmappers.findById(userId);
	}











}
