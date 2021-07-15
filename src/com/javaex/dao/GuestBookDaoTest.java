package com.javaex.dao;

import java.util.List;

import com.javaex.vo.GuestBookVo;

public class GuestBookDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GuestBookDao guestBookDao = new GuestBookDao();
		List<GuestBookVo> guestBookList = guestBookDao.getList();
		for(GuestBookVo guestBookInfo : guestBookList) {
			System.out.println(guestBookInfo);
		}
	}

}
