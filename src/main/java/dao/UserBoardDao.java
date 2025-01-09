package dao;

import java.util.List;

import model.Board;

public interface UserBoardDao {
	public List<Board>showBoardList(); // 게시판 조회
	String registerBoard(Board board); // 게시물 작성
	Board getBoardById(int boardId); // 게시물 상세 조회
	String updateBoard(Board board); // 게시물 수정
	String deleteBoard(int boardId); // 게시물 삭제
}
