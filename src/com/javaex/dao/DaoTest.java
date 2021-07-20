package com.javaex.dao;

import java.util.List;

import com.javaex.vo.BoardVo;

public class DaoTest {

	public static void main(String[] args) {

		BoardDao boardDao = new BoardDao();
		//리스트 호출
		List<BoardVo> boardList = boardDao.getList("");
		
		/*
		 * int row = boardList.size()/10; if(boardList.size() % 10 != 0) { row++; }
		 * 
		 * BoardVo[][] pageList = new BoardVo[row][10]; int x = 0; for(int r = 0; r <
		 * row; r++) { for(int i = 0; i <=9; i++) { if(i+x < boardList.size()) {
		 * pageList[r][i] = boardList.get(i+x); } } x+=10; }
		 * 
		 * for(int l=0; l<row; l++) { //배열의 각 열에 10개 단위의 BoardVo 들어감 for(int j = 0; j <=
		 * pageList[l].length; j++) { System.out.println(pageList[l][j].toString()); }
		 * System.out.println(l+"열 종료"); }
		 */
		
		
		//검색 확인
		String searchWord = "이";
		System.out.println(searchWord);
		boardList = boardDao.getList(searchWord);
		
		for(BoardVo boardInfo : boardList)
			System.out.println(boardInfo.toString());
//		
//		//게시글 하나 불러오기
//		boardDao.getBoard(42);
//		
//		//삭제
//		boardDao.delete(41, 6);
//		
//		//글 입력
//		String title ="안녕하세요";
//		String content ="반갑습니다";
//		int userNo = 6;
//		boardDao.write(title, content, userNo);
//		
//		//글 수정
//		title = "반갑습니다";
//		content = "안녕하세요";
//		int boardNo = 42;
//		boardDao.modify(title, content, boardNo);
//		
//		//조회수
//		System.out.println(boardList.get(1).getHit());
//		boardDao.hit(boardList.get(1));
//		//확인
//		System.out.println(boardList.get(1).getHit());
	}

}
