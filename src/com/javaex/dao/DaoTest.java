package com.javaex.dao;

import java.util.List;

import com.javaex.vo.GuestBookVo;
import com.javaex.vo.UserVo;

public class DaoTest {

	public static void main(String[] args) {

		UserDao userDao = new UserDao();
		UserVo userVo = new UserVo("dudry1212", "123123", "최영교", "male");
		
		int iCount = userDao.insert(userVo);
		System.out.println(iCount);
		
		GuestBookDao guestBookDao = new GuestBookDao();
		
		List<GuestBookVo> guestBookList = guestBookDao.getList();
		
		for(GuestBookVo guestBookInfo : guestBookList) {
			System.out.println(guestBookInfo);
		}
		
		userDao.getUser("aaa", "1234");
		
		System.out.println(userVo.toString());
		
	}

}
