package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("UserController");
		
		String action = request.getParameter("action");
		System.out.println(action);
		
		if("joinForm".equals(action)) {
			System.out.println("UserController_joinForm");

			//회원가입 포워드
			String path = "/WEB-INF/views/user/joinForm.jsp";
			WebUtil.forward(request, response, path);
			
		} else if ("join".equals(action)) {
			//회원가입
			System.out.println("UserController_join");
			
			//파라미터 꺼내기
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
			System.out.println(id + pw + name + gender);
			
			//Vo로 만들기
			UserVo userJoin = new UserVo(id, pw, name, gender);
			System.out.println(userJoin.toString());
			
			//dao로 db에 추가
			UserDao userDao = new UserDao();
			int iCount = userDao.insert(userJoin);
			
			System.out.println(iCount + "건 입력되었습니다.");
			
			//포워드
			String path = "/WEB-INF/views/user/joinOk.jsp";
			WebUtil.forward(request, response, path);
			
		} else if("loginForm".equals(action)) {
			System.out.println("UserController_loginForm");
			
			//포워드
			String path = "/WEB-INF/views/user/loginForm.jsp";
			WebUtil.forward(request, response, path);
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post로 받을 시, 파라미터를 인코딩할 방식 지정
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
