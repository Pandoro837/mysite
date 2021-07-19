package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("UserController");

		String action = request.getParameter("action");
		System.out.println(action);

		if ("joinForm".equals(action)) {								//회원가입 화면
			System.out.println("UserController_joinForm");

			// 회원가입 포워드
			String path = "/WEB-INF/views/user/joinForm.jsp";
			WebUtil.forward(request, response, path);

		} else if ("join".equals(action)) {								//insert sql
			// 회원가입
			System.out.println("UserController_join");

			// 파라미터 꺼내기
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");

			System.out.println(id + pw + name + gender);

			// Vo로 만들기
			UserVo userJoin = new UserVo(id, pw, name, gender);
			System.out.println(userJoin.toString());

			// dao로 db에 추가
			UserDao userDao = new UserDao();
			int iCount = userDao.insert(userJoin);

			System.out.println(iCount + "건 입력되었습니다.");

			// 포워드
			String path = "/WEB-INF/views/user/joinOk.jsp";
			WebUtil.forward(request, response, path);

		} else if ("loginForm".equals(action)) {					//login 화면
			System.out.println("UserController_loginForm");

			// 포워드
			String path = "/WEB-INF/views/user/loginForm.jsp";
			WebUtil.forward(request, response, path);

		} else if ("login".equals(action)) {						//login
			System.out.println("UserController_login");

			// 파라미터 꺼내기
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");

			// vo로 묶기

			// 회원정보 확인(세션 저장용)
			UserDao userDao = new UserDao();
			UserVo authUser = userDao.getUser(id, pw);			//id, pw, no, name 

			if (authUser.getName() != null) {						//login 성공
				System.out.println("로그인 성공");
				// 로그인에 성공 했을 때, 세션에 저장
				HttpSession session = request.getSession(); // 변수 session에 세션 저장
				session.setAttribute("authUser", authUser); // session에 authUser를 저장

				// 리다이렉트
				String url = "/mysite/main";
				WebUtil.redirect(request, response, url);
			} else {												//login 실패
				System.out.println("로그인 실패");

				String url = "/mysite/user?action=loginForm&result=fail";
				WebUtil.redirect(request, response, url);
			}

		} else if ("logout".equals(action)) {						//logout
			System.out.println("UserController_logout");

			// 세션에 있는 authUser의 정보 삭제
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();

			String url = "/mysite/main";
			WebUtil.redirect(request, response, url);
		} else if ("modifyForm".equals(action)) {					//회원정보 수정 화면
			System.out.println("UserController_modifyForm");
			
			UserDao userDao = new UserDao();
			
			HttpSession session = request.getSession();
			int no = ((UserVo)session.getAttribute("authUser")).getNo();  // no, name 
			
			UserVo userInfo = new UserVo(no);
			userInfo = userDao.modifyInfo(userInfo);				//id, pw, no, name, gender
			
			request.setAttribute("userInfo", userInfo);							//request에 저장

			String path = "/WEB-INF/views/user/modifyForm.jsp";
			WebUtil.forward(request, response, path);

		} else if ("modify".equals(action)) {						//회원정보 수정
			System.out.println("UserController_modify");

			int no = Integer.parseInt(request.getParameter("no"));
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
			System.out.println(gender);
			
			UserVo userModify = new UserVo(no, pw, name, gender);	
			UserDao userDao = new UserDao();
			
			userDao.modify(userModify);								//update
			
			System.out.println((request.getSession()).getAttribute("authUser")+"modify 세션");
			
			// modify 이후 수정된 값을 가져와서 다시 세션에 입력
			HttpSession session = request.getSession(); // 변수 session에 세션 저장
			((UserVo)session.getAttribute("authUser")).setName(name);	//세션의 authUser에 setName을 업데이트
			
			System.out.println((request.getSession()).getAttribute("authUser")+"modify 수정 후 세션");
			
			// 리다이렉트
			String url = "/mysite/main";
			WebUtil.redirect(request, response, url);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// post로 받을 시, 파라미터를 인코딩할 방식 지정
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
