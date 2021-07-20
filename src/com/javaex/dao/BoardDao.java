package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;

public class BoardDao extends DaoUtil{
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String dbId = "userdb";
	private String dbPw = "userdb";
	
	public List<BoardVo> getList(String searchWord){
		
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		
		conn = getConnection(dbId, dbPw);
		
		try {
			String query = "";
			query+=" SELECT ";
			query+="	board.no no, ";
			query+="	title, ";
			query+="	hit, ";
			query+="	TO_CHAR(reg_date,'yy/mm/dd hh24:mm:ss') reg_date, ";
			query+="	user_no, ";
			query+="	users.name name ";
			query+=" FROM ";
			query+=" 	users, ";
			query+="	board ";
			query+=" WHERE board.user_no = users.no ";
			query+="   AND (title like ? or users.name like ?)";
			query+=" ORDER BY reg_date DESC ";
			
			searchWord = "%"+searchWord+"%";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchWord);
			pstmt.setString(2, searchWord);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				int hit = rs.getInt("hit");
				String date = rs.getString("reg_date");
				int userNo = rs.getInt("user_no");
				String name = rs.getString("name");
				
				BoardVo boardVo = new BoardVo(no, title, hit, date, userNo, name);
				
				boardList.add(boardVo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(conn, pstmt, rs);
		
		return boardList;
	}
	
	public void delete(int boardNo, int userNo) {
		
		conn = getConnection(dbId, dbPw);
		
		try {
			String query="";
			query+=" DELETE FROM board ";
			query+=" WHERE no = ? ";
			query+="   AND user_no = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, userNo);

			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("실패");
			e.printStackTrace();
		}
		
		
		close(conn, pstmt, rs);
	}
	
	public BoardVo getBoard(int no) {
		
		conn = getConnection(dbId, dbPw);
		
		BoardVo boardRead = new BoardVo();
		
		try {
			String query = "";
			query+=" SELECT ";
			query+="	users.name name, ";
			query+="	hit, ";
			query+="	TO_CHAR(reg_date,'yy/mm/dd') reg_date, ";
			query+="	title, ";
			query+="	content, ";
			query+="	user_no, ";
			query+="	board.no ";
			query+=" FROM ";
			query+=" 	users, ";
			query+="	board ";
			query+=" WHERE board.user_no = users.no ";
			query+="   AND board.no = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String name = rs.getString("name");
				int hit = rs.getInt("hit");
				String date = rs.getString("reg_date");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int userNo = rs.getInt("user_no");
				int boardNo = rs.getInt("no");
				
				boardRead = new BoardVo(name, hit, date, title, content, userNo, boardNo);
			
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(conn, pstmt, rs);
		
		return boardRead;
	}
	
	public void hit(BoardVo boardVo) {
		
		int iCount = boardVo.getHit();
		iCount++;
		boardVo.setHit(iCount);
		
		conn = getConnection(dbId, dbPw);
		
		try {
			String query="";
			query+=" UPDATE board ";
			query+=" SET ";
			query+=" 	hit = ? ";
			query+=" WHERE board.no = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, iCount);
			pstmt.setInt(2, boardVo.getNo());
			
			pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("실패");
			e.printStackTrace();
		}
		
		close(conn, pstmt, rs);
		
	}
	
	public void modify(String title, String content, int boardNo) {
		
		conn = getConnection(dbId, dbPw);
		
		try {
			String query="";
			query+=" UPDATE board ";
			query+=" SET ";
			query+=" 	title = ?, ";
			query+=" 	content = ? ";
			query+=" WHERE board.no = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, boardNo);

			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("실패");
			e.printStackTrace();
		}
		
		close(conn, pstmt, rs);
		
	}
	
	public void write(String title, String content, int userNo) {
		
		conn = getConnection(dbId, dbPw);
		
		try {
			String query="";
			query+=" INSERT INTO board ";
			query+=" VALUES(seq_board_no.NEXTVAL, ?, ?, 0, sysdate, ?) ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, userNo);

			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("실패");
			e.printStackTrace();
		}
		
		close(conn, pstmt, rs);
		
	}
	
}
