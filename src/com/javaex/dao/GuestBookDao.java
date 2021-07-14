package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestBookVo;

public class GuestBookDao {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id ="guestbookdb";
	private String pw ="guestbookdb";
	
	private void getConnection(){
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
		    // 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
		 
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
	}
	private void close() {
		try {
	        if (rs != null) {
	            rs.close();
	        }                
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    } catch (SQLException e) {
	        System.out.println("error:" + e);
	    }
	}
	
	public List<GuestBookVo> getList(){
		List<GuestBookVo> guestBookList = new ArrayList<GuestBookVo>();
		
		getConnection();
		
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
		
		close();
		return guestBookList;
	}
	
	public void insert(GuestBookVo guestBookVo) {
		getConnection();
		
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
		
		close();
	}

	public void delete(String pw, int no) {
		getConnection();
		
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
		
		close();
	}
	
}
