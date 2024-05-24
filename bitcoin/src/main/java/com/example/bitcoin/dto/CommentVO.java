package com.example.bitcoin.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import lombok.Data;

@Data
public class CommentVO {

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
	public Long getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(Long boardSeq) {
		this.boardSeq = boardSeq;
	}
	public Long getHeart() {
		return heart;
	}
	public void setHeart(Long heart) {
		this.heart = heart;
	}
	Long seq;
	String textWrite;
	String writer;
	Long boardSeq;
	Long heart;
	Date writeDate;

	public String getWriteDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(writeDate);
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
}
