package com.example.bitcoin.dto;

import lombok.Data;

@Data
public class PagingVO {

	int page;
	int pageSize;
	int offSet;
	String writer;

	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
}

	public int getOffSet() {
		return offSet;
	}
	public void setOffSet(int offset) {
		this.offSet = offset;
	}
}
