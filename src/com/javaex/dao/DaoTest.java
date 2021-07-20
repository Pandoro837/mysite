package com.javaex.dao;

import java.util.List;

import com.javaex.vo.BoardVo;

public class DaoTest {

	public static void main(String[] args) {

		BoardDao boardDao = new BoardDao();
		//리스트 호출
		List<BoardVo> boardList = boardDao.getList();
		
		for(BoardVo boardInfo : boardList)
			System.out.println(boardInfo.toString());
		
		//게시글 하나 불러오기
		boardDao.getBoard(42);
		
		//삭제
		boardDao.delete(41, 6);
		
		//글 입력
		String title ="안녕하세요";
		String content ="반갑습니다";
		int userNo = 6;
		boardDao.write(title, content, userNo);
		
		//글 수정
		title = "반갑습니다";
		content = "안녕하세요";
		int boardNo = 42;
		boardDao.modify(title, content, boardNo);
		
		//조회수
		System.out.println(boardList.get(1).getHit());
		boardDao.hit(boardList.get(1));
		//확인
		System.out.println(boardList.get(1).getHit());
	}

}
