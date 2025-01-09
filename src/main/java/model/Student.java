package model;

public class Student {
    private String studentId; // 학번
    private String password;  // 비밀번호
    private String name;      // 이름
    private String university; // 대학
    private String department; // 학과

    // 기본 생성자 - 빈 객체를 생성할 때 사용
    public Student() {
    }

    // 매개변수 생성자 - 모든 필드 값 초기화
    public Student(String studentId, String password, String name, String university, String department) {
        this.studentId = studentId;
        this.password = password;
        this.name = name;
        this.university = university;
        this.department = department;
    }

    // Getter와 Setter - 각 필드 값을 가져오거나 설정할 수 있는 메서드
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // toString 메서드 (디버깅용)
    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", university='" + university + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}