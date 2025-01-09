package dao;

import model.Student;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDaoImpl implements StudentDao {

    // 회원가입 로직: 중복 확인 후 삽입
	@Override
	public String registerStudent(Student student) {
	    String checkQuery = "SELECT COUNT(*) FROM client WHERE student_id = ?";
	    String insertQuery = "INSERT INTO client (student_id, password, name, university, department) VALUES (?, ?, ?, ?, ?)";
	    
	    try (Connection connection = DBConnectionUtil.getConnection();
	         PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {

	        // 중복 확인
	        checkStmt.setString(1, student.getStudentId());
	        ResultSet rs = checkStmt.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            return "중복된 학번이 존재합니다.";
	        }
	        
	        // 데이터 삽입
	        try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
	            insertStmt.setString(1, student.getStudentId());
	            insertStmt.setString(2, student.getPassword());
	            insertStmt.setString(3, student.getName());
	            insertStmt.setString(4, student.getUniversity());
	            insertStmt.setString(5, student.getDepartment());
	            int rowsAffected = insertStmt.executeUpdate();
	            return rowsAffected > 0 ? "회원가입 성공" : "회원가입 실패";
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "데이터베이스 오류: " + e.getMessage();
	    }
	}

    // 회원가입 중복확인 메서드, 중복인지 체크하기 위해 primary key인 학번이 db에 있나 조회, 있으면 중복 처리
    @Override
    public boolean isStudentIdExists(String studentId) {
        String sql = "SELECT COUNT(*) FROM client WHERE student_id = ?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // 중복 여부 반환
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    // 로그인 로직: 학번과 비밀번호로 사용자 조회
    @Override
    public Student login(String studentId, String password) {
        String sql = "SELECT * FROM client WHERE student_id = ? AND password = ?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // 입력값 매핑
            statement.setString(1, studentId);
            statement.setString(2, password);

            // SQL 실행 및 결과 처리
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Student(
                        resultSet.getString("student_id"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("university"),
                        resultSet.getString("department")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // 조회 결과 없음
    }
}
