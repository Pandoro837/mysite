package com.javaex.vo;

public class BoardVo {
	private int no;
	private String title;
	private String content;
	private int hit;
	private String date;
	private int userNo;
	private String name;
	private int rownum;
	
	public BoardVo() {
		
	}
	
	public BoardVo(String name, int hit, String date, String title, String content, int userNo, int no) {
		this.name = name;
		this.hit = hit;
		this.date = date;
		this.title = title;
		this.content = content;
		this.userNo = userNo;
		this.no = no;
	}
	
	public BoardVo(int no, String title, int hit, String date, int userNo, String name, int rownum) {
		this.no = no;
		this.title = title;
		this.hit = hit;
		this.date = date;
		this.userNo = userNo;
		this.name = name;
		this.rownum = rownum;
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", hit=" + hit + ", date=" + date
				+ ", userNo=" + userNo + ", name=" + name + ", rownum=" + rownum + "]";
	}

}
