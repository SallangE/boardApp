package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {

    // JDBC URL, Username, Password
    private static final String URL = "jdbc:mysql://localhost:3306/board_database";
    private static final String USERNAME = "root"; // MySQL 사용자 이름
    private static final String PASSWORD = "9829"; // MySQL 비밀번호

    static {
        try {
            // MySQL 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found!", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}