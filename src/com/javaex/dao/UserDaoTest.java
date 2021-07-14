package com.javaex.dao;

import com.javaex.vo.UserVo;

public class UserDaoTest {

	public static void main(String[] args) {

		UserDao userDao = new UserDao();
		UserVo userVo = new UserVo("dudry1212", "123123", "최영교", "male");
		
		int iCount = userDao.insert(userVo);
		System.out.println(iCount);
	}

}
