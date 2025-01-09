package service;

import java.util.List;

import dao.UserBoardDao;
import dao.UserBoardDaoImpl;
import model.Board;


public class UserBoardServiceImpl implements UserBoardService {
	private UserBoardDao dao = new UserBoardDaoImpl();
	
	//전체 목록 조회 요청 로직
	@Override
	public List<Board> showBoardList() {
		return dao.showBoardList();
	}
	
	// 회원가입 로직
    @Override
    public String registerBoard(Board board) {
    	 // 입력값 검증
        if (board == null || board.getTitle().isEmpty() || board.getContent().isEmpty()) {
            return "필수 정보를 모두 입력해주세요.";
        }
        // 3. DAO 호출하여 DB에 사용자 정보 저장, 손님이 가져온 재료 보관
        return dao.registerBoard(board);
    }
    
    //dao에 board id 요청하는 로직
    @Override
    public Board getBoardById(int boardId) {
        return dao.getBoardById(boardId);
    }
    
    @Override
    public String updateBoard(Board board) {
        return dao.updateBoard(board);
    }

    @Override
    public String deleteBoard(int boardId) {
        return dao.deleteBoard(boardId);
    }

}
