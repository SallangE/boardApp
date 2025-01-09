package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Board;
import service.UserBoardService;
import service.UserBoardServiceImpl;

@WebServlet("/board")
public class UserListController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UserBoardService service = new UserBoardServiceImpl();

	
	
	@Override
	//write,submit
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 세션에서 사용자 정보를 가져옴
		HttpSession session = req.getSession();
		
		if (session == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		String studentId = (String) session.getAttribute("studentId");
		String name = (String) session.getAttribute("userName");
		
		
		//사용자가 입력한 제목과 내용을 가져옴
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		//Board 객체 생성, board 틀을 가져와서 사용자로부터 받아온 재료들을 채워 넣음
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setStudentId(studentId);
		board.setName(name);
		
		//서비스를 통해 DAO 호출, 창고(db)에 board 객체를 sql문으로 집어 넣어라
		String result = service.registerBoard(board);
		
		// 결과에 따라 페이지 이동
		if ("게시글 작성 성공".equals(result)) {
			resp.sendRedirect(req.getContextPath() + "/board?cmd=list");
		} else {
			req.setAttribute("error", result);
			req.getRequestDispatcher("/WEB-INF/views/userBoardWrite.jsp").forward(req, resp);
		}
	}

	
	//(list)게시판 목록 요청 시 처리 메소드
	private void showBoardList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("list", service.showBoardList());
		req.getRequestDispatcher("/WEB-INF/views/userBoardList.jsp").forward(req, resp);
	}
	
	
	// (view)게시물 상세보기 요청 처리 메서드
	private void viewBoard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
	    if (session == null) { // 세션 정보가 만료되었으면 로그인으로 보내기
	        resp.sendRedirect(req.getContextPath() + "/login");
	        return;
	    }
		
	    //로그인 한 아이디 변수 데이터를 세션에서 받아옴
	    String loggedInStudentId = (String) session.getAttribute("studentId");
	    
		// 요청 파라미터에서 id를 가져옴
	    String boardId = req.getParameter("id");
	    if (boardId == null || boardId.isEmpty()) {
	        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "게시물 ID가 필요합니다.");
	        return;
	    }

	    // 서비스 계층을 통해 게시물 정보를 가져옴
	    Board board = service.getBoardById(Integer.parseInt(boardId));
	    if (board == null) {
	        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 게시물을 찾을 수 없습니다.");
	        return;
	    }

	    // 조회한 게시물의 작성자인지 여부를 확인한 정보를 가져오고, 조회된 게시물 정보를 요청 객체에 저장
	    boolean isOwner = board.getStudentId().equals(loggedInStudentId);
	    req.setAttribute("board", board);
	    req.setAttribute("isOwner", isOwner);

	    // 상세보기 페이지(jsp)로 포워딩
	    req.getRequestDispatcher("/WEB-INF/views/userBoardView.jsp").forward(req, resp);
	}
	
	//(edit) 게시글 수정 이동 및 요청 메소드
	private void editBoard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String boardIdParam = req.getParameter("id");
	    if (boardIdParam == null || boardIdParam.isEmpty()) {
	        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "게시물 ID가 필요합니다.");
	        return;
	    }

	    int boardId = Integer.parseInt(boardIdParam);
	    Board board = service.getBoardById(boardId);

	    if (board == null) {
	        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 게시물을 찾을 수 없습니다.");
	        return;
	    }

	    req.setAttribute("board", board);
	    req.getRequestDispatcher("/WEB-INF/views/userBoardEdit.jsp").forward(req, resp); // 수정 페이지로 이동
	}

	
	//(delete) 게시글 삭제 메소드
	private void deleteBoard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    
		int boardId = Integer.parseInt(req.getParameter("id"));
	    String result = service.deleteBoard(boardId); // 서비스 호출
	    
	    if ("삭제 성공".equals(result)) {
	        System.out.println("삭제 성공: 리디렉션 실행");
	        resp.sendRedirect(req.getContextPath() + "/board?cmd=list");
	    } else {
	        System.out.println("삭제 실패: 에러 페이지로 포워딩");
	        req.setAttribute("error", result);
	        req.getRequestDispatcher("/WEB-INF/views/userBoardList.jsp").forward(req, resp);
	    }
	}
	
	//(update)수정 실행 메소드
	private void updateBoard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String boardIdParam = req.getParameter("id");
	    String title = req.getParameter("title");
	    String content = req.getParameter("content");

	    if (boardIdParam == null || title == null || content == null ||
	        boardIdParam.isEmpty() || title.isEmpty() || content.isEmpty()) {
	        req.setAttribute("error", "모든 필드를 입력해야 합니다.");
	        req.getRequestDispatcher("/WEB-INF/views/editBoard.jsp").forward(req, resp);
	        return;
	    }

	    int boardId = Integer.parseInt(boardIdParam);
	    Board board = new Board();
	    board.setBoard_id(boardId);
	    board.setTitle(title);
	    board.setContent(content);

	    String result = service.updateBoard(board);

	    if ("수정 성공".equals(result)) {
	        resp.sendRedirect(req.getContextPath() + "/board?cmd=view&id=" + boardId);
	    } else {
	        req.setAttribute("error", result);
	        req.getRequestDispatcher("/WEB-INF/views/userBoardEdit.jsp").forward(req, resp);
	    }
	}

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cmd = req.getParameter("cmd");
		System.out.println(cmd);
		switch (cmd) {
		case "list":
			showBoardList(req,resp);
			break;
		case "write":
			req.getRequestDispatcher("/WEB-INF/views/userBoardWrite.jsp").forward(req, resp);
			break;
		case "submit":
			doPost(req,resp); //게시글 작성 처리
			break;
		case "view":
			viewBoard(req,resp);
			break;
		case "edit":
			editBoard(req,resp);
			break;
		case "delete":
			deleteBoard(req,resp);
			break;
		case "update":
			updateBoard(req, resp);
			break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + cmd);
		}
	}
	
	
}
