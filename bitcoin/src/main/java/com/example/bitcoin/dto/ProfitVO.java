package com.example.bitcoin.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class ProfitVO {

	String id;
	String profit;
	String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	Date profit_date;


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProfit() {
		return profit;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}
	public String getProfit_date() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(profit_date);
	}
	public void setProfit_date(Date profit_date) {
		this.profit_date = profit_date;
	}

}
