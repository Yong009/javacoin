package com.example.bitcoin.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class MarketVO {

	String market;
	String koreanName;
	String englishName;

	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getKoreanName() {
		return koreanName;
	}
	public void setKoreanName(String koreanName) {
		this.koreanName = koreanName;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
}
