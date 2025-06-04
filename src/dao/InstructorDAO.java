package dao;

import util.DBConnection;

import java.sql.*;

public class InstructorDAO {
    public void addInstructor(int id, String name, int deptId, double salary) throws SQLException {
        String sql = "INSERT INTO Instructors (Instructor_ID, Name, Department_ID, Salary) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setInt(3, deptId);
            stmt.setDouble(4, salary);
            stmt.executeUpdate();
        }
    }

    public void updateSalary(int id, double newSalary) throws SQLException {
        String sql = "UPDATE Instructors SET Salary = ? WHERE Instructor_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, newSalary);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public void deleteInstructor(int id) throws SQLException
    {
        String sql = "DELETE FROM Instructors WHERE Instructor_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Update instructor's name
    public void updateInstructorName(int id, String newName) throws SQLException
    {
        String sql = "UPDATE Instructors SET Name = ? WHERE Instructor_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, newName);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    // Update instructor's email
    public void updateInstructorEmail(int id, String newEmail) throws SQLException
    {
        String sql = "UPDATE Instructors SET Email = ? WHERE Instructor_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, newEmail);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    // Update instructor's department
    public void updateInstructorDepartment(int id, int newDeptId) throws SQLException
    {
        String sql = "UPDATE Instructors SET Department_ID = ? WHERE Instructor_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, newDeptId);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    // Print 10% income tax for each instructor
    public void printIncomeTaxForAllInstructors() throws SQLException
    {
        String sql = "SELECT Instructor_ID, Name, Salary FROM Instructors";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {

            while (rs.next())
            {
                int id = rs.getInt("Instructor_ID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");
                double tax = salary * 0.10;

                System.out.printf("Instructor ID: %d, Name: %s, Tax (10%%): $%.2f%n", id, name, tax);
            }
        }
    }
}