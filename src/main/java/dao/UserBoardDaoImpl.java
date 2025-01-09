package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Board;
import util.DBConnectionUtil;

public class UserBoardDaoImpl implements UserBoardDao {

	@Override
	public List<Board> showBoardList() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM board";
		List<Board> list = new ArrayList<>();
		try (Connection conn = DBConnectionUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Board board = new Board();
				board.setBoard_id(rs.getInt("board_id"));
				board.setStudentId(rs.getString("student_id"));
				board.setName(rs.getString("name"));
				board.setContent(rs.getString("content"));
				board.setTitle(rs.getString("title"));
				board.setCreated_at(rs.getString("created_at"));
				board.setView(rs.getInt("view"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String registerBoard(Board board) {
		String insertQuery = "INSERT INTO board (title, content, student_id, name) VALUES (?, ?, ?, ?)";

		try (Connection connection = DBConnectionUtil.getConnection();
				PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {

			//board_id(게시물 고유 번호)는 AUTO_INCREMENT 속성이므로 생성 시에 자동으로 증가해서 처리됨
			//view(조회수)는 새로 생성될 때마다 항상 0으로 값이 고정되어 있으므로 명시적으로 값을 줄 필요가 없음
			//created_at 역시 timestamp이므로 db에 기록 시에 자동으로 기록되니까 명시적으로 값을 넣을 필요 없음
			insertStmt.setString(1, board.getTitle());
			insertStmt.setString(2, board.getContent());
			insertStmt.setString(3, board.getStudentId()); // 세션에서 전달된 작성자 ID
	        insertStmt.setString(4, board.getName());       // 세션에서 전달된 작성자 이름

			int rowsAffected = insertStmt.executeUpdate();
			return rowsAffected > 0 ? "게시글 작성 성공" : "게시글 작성 실패";

		} catch (SQLException e) {
			e.printStackTrace();
			return "데이터베이스 오류: " + e.getMessage();
		}
	}
	
	
	// 특정 게시물 상세 조회 dao
	@Override
	public Board getBoardById(int boardId) {
	    String sql = "SELECT * FROM board WHERE board_id = ?";
	    try (Connection conn = DBConnectionUtil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, boardId);
	        ResultSet rs = stmt.executeQuery(); {
	            if (rs.next()) {
	                Board board = new Board();
	                board.setBoard_id(rs.getInt("board_id"));
	                board.setTitle(rs.getString("title"));
	                board.setContent(rs.getString("content"));
	                board.setStudentId(rs.getString("student_id"));
	                board.setName(rs.getString("name"));
	                board.setCreated_at(rs.getString("created_at"));
	                board.setView(rs.getInt("view"));
	                return board;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	//수정 daoImpl
	@Override
	public String updateBoard(Board board) {
	    String sql = "UPDATE board SET title = ?, content = ? WHERE board_id = ?";
	    try (Connection conn = DBConnectionUtil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, board.getTitle());
	        stmt.setString(2, board.getContent());
	        stmt.setInt(3, board.getBoard_id());
	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0 ? "수정 성공" : "수정 실패";
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "데이터베이스 오류: " + e.getMessage();
	    }
	}

	//삭제 daoImpl
	@Override
	public String deleteBoard(int boardId) {
	    String sql = "DELETE FROM board WHERE board_id = ?";
	    try (Connection conn = DBConnectionUtil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, boardId);
	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0 ? "삭제 성공" : "삭제 실패";
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "데이터베이스 오류: " + e.getMessage();
	    }
	}
	
}
