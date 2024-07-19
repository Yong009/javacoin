package com.example.bitcoin.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class NoticeVO {

	public Long seq;			//번호
	public String title;		//제목

	public Date writeDate;		//작성일자

	public String textWrite;	//글


	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriteDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(writeDate);
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public String getTextWrite() {
		return textWrite;
	}
	public void setTextWrite(String textWrite) {
		this.textWrite = textWrite;
	}

}
