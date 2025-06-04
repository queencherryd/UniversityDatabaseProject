
package dao;

import util.DBConnection;

import java.sql.*;
import java.util.*;

public class StudentDAO {
    public void addStudent(int id, String name, int age, int departmentId) throws SQLException {
        String sql = "INSERT INTO Students (Student_ID, Name, Age, Department_ID) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setInt(3, age);
            stmt.setInt(4, departmentId);
            stmt.executeUpdate();
        }
    }

    public List<String> getAllStudents() throws SQLException {
        List<String> students = new ArrayList<>();
        String sql = "SELECT * FROM Students";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(rs.getInt("Student_ID") + " - " + rs.getString("Name"));
            }
        }
        return students;
    }

    public void updateStudentName(int id, String newName) throws SQLException {
        String sql = "UPDATE Students SET Name = ? WHERE Student_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newName);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public void deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM Students WHERE Student_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
