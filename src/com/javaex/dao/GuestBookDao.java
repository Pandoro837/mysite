package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestBookVo;

public class GuestBookDao extends DaoUtil {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String dbId ="guestbookdb";
	private String dbPw ="guestbookdb";
	
	public List<GuestBookVo> getList(){
		
		List<GuestBookVo> guestBookList = new ArrayList<GuestBookVo>();
		
		conn = super.getConnection(dbId, dbPw);
		
		try {
			String query = "";
			query+= " SELECT ";
			query+= " 		no, ";
			query+= " 		name, ";
			query+= " 		pw, ";
			query+= " 		content, ";
			query+= " 		TO_CHAR(reg_date, 'yyyy-mm-dd hh24:mm:ss') reg_date ";
			query+= " FROM ";
			query+= " 		guestbook ";
			query+= " ORDER BY reg_date ASC ";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String pw = rs.getString("pw");
				String content = rs.getString("content");
				String date = rs.getString("reg_date");
				GuestBookVo guestBookVo = new GuestBookVo(no, name, pw, content, date);
				guestBookList.add(guestBookVo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		super.close(conn, pstmt, rs);
		return guestBookList;
	}
	
	public void insert(GuestBookVo guestBookVo) {

		conn = super.getConnection(dbId, dbPw);
		
		try {
			String query = "";
			query+= " INSERT INTO guestbook ";
			query+= " VALUES(seq_no.NEXTVAL, ?, ?, ?, SYSDATE) ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, guestBookVo.getName());
			pstmt.setString(2, guestBookVo.getPw());
			pstmt.setString(3, guestBookVo.getContent());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		super.close(conn, pstmt, rs);
	}

	public void delete(String pw, int no) {
		
		conn = super.getConnection(dbId, dbPw);
		
		try {
			String query = "";
			query+= " DELETE ";
			query+= " FROM ";
			query+= " guestbook ";
			query+= " WHERE pw = ? ";
			query+= "   AND no = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pw);
			pstmt.setInt(2, no);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		super.close(conn, pstmt, rs);
	}
	
}
