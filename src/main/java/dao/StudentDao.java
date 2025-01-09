package dao;

import model.Student;

public interface StudentDao {
	String registerStudent(Student student); // 회원가입
	boolean isStudentIdExists(String studentId); // 회원가입 중복 확인
	Student login(String studentId, String password); //로그인
}
