package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "userdb";
	private String pw = "userdb";
	
	public void getConnection() {
		
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
	
	public void close() {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}                
		
	
	public int insert(UserVo userVo) {
		int iCount = -1;
		
		getConnection();
		
		String query = "";
		query+= " INSERT INTO users ";
		query+= " VALUES(seq_user_no.NEXTVAL, ?, ?, ?, ?) ";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userVo.getId());
			pstmt.setString(2, userVo.getPw());
			pstmt.setString(3, userVo.getName());
			pstmt.setString(4, userVo.getGender());
			
			iCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close();
		
		return iCount;
	}
	
}
