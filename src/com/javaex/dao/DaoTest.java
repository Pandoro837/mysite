package com.javaex.dao;

import java.util.List;

import com.javaex.vo.BoardVo;

public class DaoTest {

	public static void main(String[] args) {

		BoardDao boardDao = new BoardDao();
		List<BoardVo> boardList = boardDao.getList();
		
		for(BoardVo boardInfo : boardList)
			System.out.println(boardInfo.toString());
		
//		boardDao.delete(6);
	}

}
