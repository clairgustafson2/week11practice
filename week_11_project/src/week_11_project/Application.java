package week_11_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String connectionString = "jdbc:mysql://localhost:3306/employees"; //localhost is an alias to 127.0.0.1
		final String SELECT_QUERY = "SELECT * FROM employees WHERE emp_no = ?";//the ? is the parameter
		
		Scanner scanner= new Scanner(System.in);
		
		try {
			Connection conn = DriverManager.getConnection(connectionString, "root", "1213Klove!");
			System.out.println("Connected successfully!");
			System.out.println("Enter employee number: ");
			
			String empNo = scanner.nextLine();
			
			PreparedStatement ps = conn.prepareStatement(SELECT_QUERY); //a prepared statement doesn't allow users to input extra SQL
			ps.setString(1, empNo); //passes in parameters. empNo is grabbed from the scanner. 1 =?
			ResultSet rs = ps.executeQuery();
			
			//this will put the result set on the next row each time until it is out of rows
			while (rs.next()) { // rs.getInt() put the column number in the () and it will get the what is in the column
				System.out.println("emp no: " + rs.getInt(1) + " dob: " + rs.getString(2) + " first name: " + rs.getString(3));
			}

		} catch (SQLException e) {
			System.out.println("Error connecting to the database.");
			e.printStackTrace();
		}

	}

}
