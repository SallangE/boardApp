package controller;

import model.Student;
import service.StudentService;
import service.StudentServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class StudentController extends HttpServlet {
    private StudentService studentService = new StudentServiceImpl();
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // register.jsp 페이지로 포워딩
        request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
    }
    
    // POST 요청: 회원가입 처리
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 폼 데이터 가져오기
        String studentId = request.getParameter("studentId");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String university = request.getParameter("university");
        String department = request.getParameter("department");

        // Student 객체 생성
        Student student = new Student(studentId, password, name, university, department);

        // 회원가입 서비스 호출
        String resultMessage = studentService.registerStudent(student);

        if ("회원가입 성공".equals(resultMessage)) {
            response.sendRedirect("login");
        } else {
            // 회원가입 실패: 에러 메시지와 함께 다시 폼 표시
            request.setAttribute("error", resultMessage);
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
        }
    }
}

