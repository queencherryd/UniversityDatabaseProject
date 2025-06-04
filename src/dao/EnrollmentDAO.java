package dao;

import util.DBConnection;

import java.sql.*;

public class EnrollmentDAO {
    public void enrollStudent(int enrollId, int studentId, int courseId, String grade) throws SQLException {
        String sql = "INSERT INTO Enrollments (Enrollment_ID, Student_ID, Course_ID, Grade) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, enrollId);
            stmt.setInt(2, studentId);
            stmt.setInt(3, courseId);
            stmt.setString(4, grade);
            stmt.executeUpdate();
        }
    }

    public void updateGrade(int enrollId, String newGrade) throws SQLException {
        String sql = "UPDATE Enrollments SET Grade = ? WHERE Enrollment_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newGrade);
            stmt.setInt(2, enrollId);
            stmt.executeUpdate();
        }
    }

    public void deleteEnrollment(int enrollId) throws SQLException
    {
        String sql = "DELETE FROM Enrollments WHERE Enrollment_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, enrollId);
            stmt.executeUpdate();
        }
    }

    // Get the student with the highest grade from Enrollments table
    public void getStudentWithHighestGrade() throws SQLException {
        String sql = """
        SELECT s.Student_ID, s.Name, s.Age, s.Email, s.Department_ID, e.Grade
        FROM Students s
        JOIN Enrollments e ON s.Student_ID = e.Student_ID
        ORDER BY
            CASE
                WHEN e.Grade = 'A+' THEN 1
                WHEN e.Grade = 'A' THEN 2
                WHEN e.Grade = 'B+' THEN 3
                WHEN e.Grade = 'B' THEN 4
                WHEN e.Grade = 'C' THEN 5
                WHEN e.Grade = 'D' THEN 6
                WHEN e.Grade = 'F' THEN 7
                ELSE 8
            END
        LIMIT 1
    """;

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                int studentId = rs.getInt("Student_ID");
                String name = rs.getString("Name");
                int age = rs.getInt("Age");
                String email = rs.getString("Email");
                int deptId = rs.getInt("Department_ID");
                String grade = rs.getString("Grade");

                System.out.println("Top Student: " + name + " (ID: " + studentId + ")");
                System.out.println("Age: " + age + ", Email: " + email + ", Department ID: " + deptId);
                System.out.println("Grade: " + grade);
            } else {
                System.out.println("No students found.");
            }
        }
    }
}