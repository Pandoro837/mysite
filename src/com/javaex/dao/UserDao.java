package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao extends DaoUtil {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String dbId = "userdb";
	private String dbPw = "userdb";
	
	//회원가입
	public int insert(UserVo userVo) {			//회원가입용 메소드
		int iCount = -1;
		
		conn = super.getConnection(dbId, dbPw);
		
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
		
		super.close(conn, pstmt, rs);
		
		return iCount;
	}
	
	public UserVo getUser(String id, String pw) {		//유저 로그인용 메소드
		
		conn = super.getConnection(dbId, dbPw);
		
		UserVo authUser = new UserVo();
		
		String query = "";
		query+= " SELECT no, name ";
		query+= " FROM users ";
		query+= " WHERE id = ? ";
		query+= "   AND password = ? ";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				
				authUser.setNo(no);
				authUser.setName(name);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		super.close(conn, pstmt, rs);
		
		return	authUser;
	}
	
	public UserVo modifyInfo(UserVo userVo) {		//modify에 필요한 정보만 올려주는 메소드
		
		conn = super.getConnection(dbId, dbPw);
		
		String query = "";
		query+= " SELECT id, ";
		query+= " 		 password, ";
		query+= " 		 name, ";
		query+= " 		 gender ";
		query+= " FROM users ";
		query+= " WHERE no = ? ";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, userVo.getNo());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String id = rs.getString("id");
				String pw = rs.getString("password");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				
				userVo.setId(id);
				userVo.setPw(pw);
				userVo.setName(name);
				userVo.setGender(gender);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.close(conn, pstmt, rs);
		
		return	userVo;
		
	}
	
	public void modify(UserVo userVo) {
		
		conn = super.getConnection(dbId, dbPw);
		
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
		
		super.close(conn, pstmt, rs);
		
	}
	
}
