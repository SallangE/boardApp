package controller;

import model.Student;
import service.StudentService;
import service.StudentServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // register.jsp 페이지로 포워딩
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        String password = request.getParameter("password");

        // 로그인 검증
        Student student = studentService.login(studentId, password);

        if ("admin".equals(studentId) && "1234".equals(password)) {
            // 관리자 로그인 성공
            HttpSession session = request.getSession();
            session.invalidate(); // 기존 세션 무효화
            session = request.getSession(true); // 새로운 세션 생성
            session.setAttribute("userRole", "admin");
            session.setAttribute("userName", "관리자");
            response.sendRedirect("adminHome");
        } else if (student != null) {
            // 일반 사용자 로그인 성공
            HttpSession session = request.getSession();
            session.invalidate(); // 기존 세션 무효화
            session = request.getSession(true); // 새로운 세션 생성
            session.setAttribute("userRole", "user");
            session.setAttribute("userName", student.getName());
            session.setAttribute("studentId", studentId);
            session.setAttribute("university", student.getUniversity()); // 추가 정보 저장
            session.setAttribute("department", student.getDepartment()); // 추가 정보 저장
            response.sendRedirect("userHome");
        } else {
            // 로그인 실패
            request.setAttribute("error", "잘못된 학번 또는 비밀번호입니다.");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}

