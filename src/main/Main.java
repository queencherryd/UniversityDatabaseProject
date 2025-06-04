package main;

import dao.InstructorDAO;
import dao.StudentDAO;
import dao.EnrollmentDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InstructorDAO instructorDAO = new InstructorDAO();
        StudentDAO studentDAO = new StudentDAO();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- University Database CLI ---");
            System.out.println("1. Add Instructor");
            System.out.println("2. Update Instructor Salary");
            System.out.println("3. Delete Instructor");
            System.out.println("4. Add Student");
            System.out.println("5. Update Student Name");
            System.out.println("6. Delete Student");
            System.out.println("7. Enroll Student in Course");
            System.out.println("8. Get Top Student Per Semester");
            System.out.println("9. Print income tax for all instructors");
            System.out.println("10. Exit");
            
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Instructor ID: ");
                        int instructorId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter Instructor Name: ");
                        String instructorName = scanner.nextLine();
                        System.out.print("Enter Department ID: ");
                        int deptId = scanner.nextInt();
                        System.out.print("Enter Salary: ");
                        double salary = scanner.nextDouble();
                        instructorDAO.addInstructor(instructorId, instructorName, deptId, salary);
                        System.out.println("Instructor added successfully!");
                        break;

                    case 2:
                        System.out.print("Enter Instructor ID: ");
                        int updateInstructorId = scanner.nextInt();
                        System.out.print("Enter New Salary: ");
                        double newSalary = scanner.nextDouble();
                        instructorDAO.updateSalary(updateInstructorId, newSalary);
                        System.out.println("Instructor salary updated successfully!");
                        break;

                    case 3:
                        System.out.print("Enter Instructor ID: ");
                        int deleteInstructorId = scanner.nextInt();
                        instructorDAO.deleteInstructor(deleteInstructorId);
                        System.out.println("Instructor deleted successfully!");
                        break;

                    case 4:
                        System.out.print("Enter Student ID: ");
                        int studentId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter Student Name: ");
                        String studentName = scanner.nextLine();
                        System.out.print("Enter Age: ");
                        int age = scanner.nextInt();
                        System.out.print("Enter Department ID: ");
                        int studentDeptId = scanner.nextInt();
                        studentDAO.addStudent(studentId, studentName, age, studentDeptId);
                        System.out.println("Student added successfully!");
                        break;

                    case 5:
                        System.out.print("Enter Student ID: ");
                        int updateStudentId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter New Name: ");
                        String newName = scanner.nextLine();
                        studentDAO.updateStudentName(updateStudentId, newName);
                        System.out.println("Student name updated successfully!");
                        break;

                    case 6:
                        System.out.print("Enter Student ID: ");
                        int deleteStudentId = scanner.nextInt();
                        studentDAO.deleteStudent(deleteStudentId);
                        System.out.println("Student deleted successfully!");
                        break;

                    case 7:
                        System.out.print("Enter Enrollment ID: ");
                        int enrollId = scanner.nextInt();
                        System.out.print("Enter Student ID: ");
                        int enrollStudentId = scanner.nextInt();
                        System.out.print("Enter Course ID: ");
                        int courseId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter Grade: ");
                        String grade = scanner.nextLine();
                        enrollmentDAO.enrollStudent(enrollId, enrollStudentId, courseId, grade);
                        System.out.println("Student enrolled successfully!");
                        break;

                    case 8:
                        enrollmentDAO.getStudentWithHighestGrade();
                        break;

                    case 9:
                        instructorDAO.printIncomeTaxForAllInstructors();
                        break;

                    case 10:
                        System.out.println("Exiting... Goodbye!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        }
    }
}
