package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("BoardController");
		
		String action = request.getParameter("action");
		System.out.println(action);
		
		if("list".equals(action)) {     //리스트 출력
			System.out.println("BoardController_list");
			
			//boardDao 호출
			BoardDao boardDao = new BoardDao();
			
			//db 읽어오기
			List<BoardVo> boardList = boardDao.getList();
			
			request.setAttribute("boardList", boardList);
			
			String path ="WEB-INF/views/board/list.jsp";
			WebUtil.forward(request, response, path);
			
		} else if("delete".equals(action)) {
			System.out.println("BoardController_delete");
			
			int boardNo = Integer.parseInt(request.getParameter("no"));
			
			//boardDao 호출
			BoardDao boardDao = new BoardDao();
			
			//No를 세션에서 가져온 이유 - list에서 링크로 넘길 경우, 누군가가 파라미터 값을 이용하여
			//타인의 글을 지울 수 있기 때문
			HttpSession session = request.getSession();
			
			//게시글 삭제
			int userNo = ((UserVo)session.getAttribute("authUser")).getNo();
			boardDao.delete(boardNo, userNo);
			
			String url ="/mysite/board?action=list";
			WebUtil.redirect(request, response, url);
			
		} else if("read".equals(action)) {
			System.out.println("BoardController_read");
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			//boardDao 호출
			BoardDao boardDao = new BoardDao();
			
			BoardVo boardRead = boardDao.getBoard(no);
			
			request.setAttribute("boardRead", boardRead);
			
			String path ="WEB-INF/views/board/read.jsp";
			WebUtil.forward(request, response, path);
			
		} else if("".equals(action)) {
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
