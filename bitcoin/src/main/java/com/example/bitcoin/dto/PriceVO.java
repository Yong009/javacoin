package com.example.bitcoin.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PriceVO {

	BigDecimal highPrice;
	BigDecimal lowPrice;
	int seq;


	public BigDecimal getHighPrice() {
		return highPrice;
	}
	public void setHighPrice(BigDecimal highPrice) {
		this.highPrice = highPrice;
	}


	public BigDecimal getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}

}
