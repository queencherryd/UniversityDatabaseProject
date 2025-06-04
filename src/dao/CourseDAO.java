package dao;

import util.DBConnection;

import java.sql.*;
import java.util.*;

public class CourseDAO {
    public void addCourse(int id, String name, int credits, int departmentId, int instructorId) throws SQLException {
        String sql = "INSERT INTO Courses (Course_ID, Course_Name, Credits, Department_ID, Instructor_ID) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setInt(3, credits);
            stmt.setInt(4, departmentId);
            stmt.setInt(5, instructorId);
            stmt.executeUpdate();
        }
    }

    public List<String> getAllCourses() throws SQLException {
        List<String> courses = new ArrayList<>();
        String sql = "SELECT * FROM Courses";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                courses.add(rs.getInt("Course_ID") + " - " + rs.getString("Course_Name"));
            }
        }
        return courses;
    }

    public void updateCourseName(int id, String newName) throws SQLException {
        String sql = "UPDATE Courses SET Course_Name = ? WHERE Course_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newName);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public void deleteCourse(int id) throws SQLException {
        String sql = "DELETE FROM Courses WHERE Course_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
