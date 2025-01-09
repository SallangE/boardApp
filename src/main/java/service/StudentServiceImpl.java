package service;

import dao.StudentDao;
import dao.StudentDaoImpl;
import model.Student;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao = new StudentDaoImpl();

 // 회원가입 로직
    @Override
    public String registerStudent(Student student) {
    	 // 입력값 검증
        if (student == null || student.getStudentId().isEmpty() || student.getPassword().isEmpty()) {
            return "필수 정보를 모두 입력해주세요.";
        }
        // 3. DAO 호출하여 DB에 사용자 정보 저장, 손님이 가져온 재료 보관
        return studentDao.registerStudent(student);
    }
    
    
    // 로그인 로직
    @Override
    public Student login(String studentId, String password) {
        // 1. 입력값 검증
        if (studentId == null || studentId.isEmpty() || password == null || password.isEmpty()) {
            return null; // 잘못된 입력
        }
        // 2. DAO 호출하여 DB에서 사용자 정보 조회, 손님이 자기 재료로 주문을 시도, 맞는 재료면 ok, 손님이 창고에 안보낸 재료면 거절
        return studentDao.login(studentId, password);
    }
}