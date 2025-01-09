package service;

import model.Student;

public interface StudentService {
	String registerStudent(Student student);
	Student login(String studentId, String password);
}
