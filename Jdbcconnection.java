
package Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Jdbcconnection {

    static final String URL = "jdbc:mysql://localhost:3306/College";
    static final String USER = "root";
    static final String PASSWORD = "Gowtham@7032";

    // Database connection method
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 1. INSERT STUDENT
    public static void insertStudent() {

        try {
            Connection con = getConnection();

            String sql = "INSERT INTO Student VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Student ID: ");
            ps.setInt(1, sc.nextInt());

            sc.nextLine();

            System.out.print("Enter Student Name: ");
            ps.setString(2, sc.nextLine());

            System.out.print("Enter Age: ");
            ps.setInt(3, sc.nextInt());

            sc.nextLine();

            System.out.print("Enter Gender: ");
            ps.setString(4, sc.nextLine());

            System.out.print("Enter City: ");
            ps.setString(5, sc.nextLine());

            System.out.print("Enter Marks: ");
            ps.setInt(6, sc.nextInt());

            int rows = ps.executeUpdate();

            System.out.println(rows + " student inserted!");

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 2. SELECT STUDENTS
    public static void selectStudents() {

        try {
            Connection con = getConnection();
            String query = "SELECT * FROM Student ORDER BY Student_id ASC";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("ID: " + rs.getInt("student_id"));
                System.out.println("Name: " + rs.getString("student_name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Gender: " + rs.getString("gender"));
                System.out.println("City: " + rs.getString("city"));
                System.out.println("Marks: " + rs.getInt("marks"));

                System.out.println("-----------------------");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3. UPDATE STUDENT
    public static void updateStudent() {

        try {
            Connection con = getConnection();

            Scanner sc = new Scanner(System.in);

            String sql = "UPDATE Student SET marks = ? WHERE student_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            System.out.print("Enter New Marks: ");
            int marks = sc.nextInt();

            ps.setInt(1, marks);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            System.out.println(rows + " student updated!");

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4. DELETE STUDENT
    public static void deleteStudent() {

        try {
            Connection con = getConnection();

            Scanner sc = new Scanner(System.in);

            String sql = "DELETE FROM Student WHERE student_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter Student ID to delete: ");

            int id = sc.nextInt();

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            System.out.println(rows + " student deleted!");

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // MAIN METHOD
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Insert Student");
            System.out.println("2. Display Students");
            System.out.println("3. Update Student Marks");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    insertStudent();
                    break;

                case 2:
                    selectStudents();
                    break;

                case 3:
                    updateStudent();
                    break;

                case 4:
                    deleteStudent();
                    break;

                case 5:
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
