package com.example.bitcoin.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class QuestionVO {
	Long seq;
	String textWrite;
	String writer;
	String admin;
	Date writeDate;


	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getTextWrite() {
		return textWrite;
	}
	public void setTextWrite(String textWrite) {
		this.textWrite = textWrite;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getWrtieDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(writeDate);
	}
	public void setWrtieDate(Date writeDate) {
		this.writeDate = writeDate;
	}

}
