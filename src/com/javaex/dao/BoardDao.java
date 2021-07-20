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
	
	public List<BoardVo> getList(){
		
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
			query+=" ORDER BY reg_date DESC ";
			
			pstmt = conn.prepareStatement(query);
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
			query+="	title, ";
			query+="	content, ";
			query+="	hit, ";
			query+="	TO_CHAR(reg_date,'yy/mm/dd') reg_date, ";
			query+="	users.name name ";
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
				
				boardRead = new BoardVo(name, hit, date, title, content);
			
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(conn, pstmt, rs);
		
		return boardRead;
	}
	
}
