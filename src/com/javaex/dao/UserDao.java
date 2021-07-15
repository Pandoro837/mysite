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
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "userdb";
	private String pw = "userdb";
	
	
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
	
	//회원가입
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
	
	public UserVo getUser(UserVo userVo) {
		
		getConnection();
		
		String query = "";
		query+= " SELECT no, name, gender ";
		query+= " FROM users ";
		query+= " WHERE id = ? ";
		query+= "   AND password = ? ";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userVo.getId());
			pstmt.setString(2, userVo.getPw());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				
				userVo.setNo(no);
				userVo.setName(name);
				userVo.setGender(gender);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close();
		
		return	userVo;
	}
	
	public UserVo modify(UserVo userVo) {
		
		getConnection();
		
		String query = "";
		query+= " UPDATE users ";
		query+= " SET password = ?, ";
		query+= "     name = ?, ";
		query+= "     gender = ? ";
		query+= " WHERE no = ? ";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userVo.getPw());
			pstmt.setString(2, userVo.getName());
			pstmt.setString(3, userVo.getGender());
			pstmt.setInt(4, userVo.getNo());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close();
		
		return this.getUser(userVo);
		
	}
	
}
