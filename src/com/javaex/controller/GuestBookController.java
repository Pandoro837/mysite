package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestBookVo;

@WebServlet("/guestbook")
public class GuestBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("GuestBookController");
		
		String action = request.getParameter("action");
		System.out.println(action);
		
		if("addList".equals(action)) {
			System.out.println("GuestBookController_addList");
			
			//guestBookDao 호출
			GuestBookDao guestBookDao = new GuestBookDao();
			
			//db 읽어오기
			List<GuestBookVo> guestBookList = guestBookDao.getList();
			
			//attribute에 담기
			request.setAttribute("guestBookList", guestBookList);
			
			//addList로 포워드
			String path = "/WEB-INF/views/guestbook/addList.jsp";
			WebUtil.forward(request, response, path);
			
		} else if("add".equals(action)) {
			System.out.println("GuestBookController_add");
			
			//guestBookDao 호출
			GuestBookDao guestBookDao = new GuestBookDao();
			
			//파라미터 꺼내기
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			//파라미터 확인
			System.out.println(name + password + content);
			
			//vo로 묶기
			GuestBookVo guestBookAdd = new GuestBookVo(name, password, content);
			
			//insert
			guestBookDao.insert(guestBookAdd);
			
			//redirect
			String url = "/mysite/guestbook?action=addList";
			WebUtil.redirect(request, response, url);
			
		} else if("deleteForm".equals(action)) {
			System.out.println("GuestBookController_deleteForm");
			
			//deleteForm으로 포워드
			String path = "/WEB-INF/views/guestbook/deleteForm.jsp";
			WebUtil.forward(request, response, path);
		} else if("delete".equals(action)) {
			//파라미터 꺼내기
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			
			//dao 삭제
			GuestBookDao guestBookDao = new GuestBookDao();
			guestBookDao.delete(password, no);
			
			//redirect
			String url = "/mysite/guestbook?action=addList";
			WebUtil.redirect(request, response, url);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
