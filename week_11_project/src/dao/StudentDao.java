package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Student;

public class StudentDao {
	
	private Connection connection;
	private final String GET_STUDENT_BY_PERIOD_ID_QUERY = "SELECT * FROM students WHERE period_id = ?";
	private final String DELETE_STUDENTS_BY_PERIOD_ID_QUERY = "DELETE FROM students WHERE period_id = ?";
	private final String CREATE_NEW_STUDENT_QUERY = "INSERT INTO students(first_name, last_name, period_id) VALUES (?, ?, ?)";
	private final String DELETE_STUDENT_BY_ID_QUERY = "DELETE FROM students WHERE id = ?";
	
	public StudentDao() {
		connection = DBconnection.getConnection();
	}

	// 1. makes a list of students by their period id
	public List<Student> getStudentsByPeriodId(int periodId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_STUDENT_BY_PERIOD_ID_QUERY);
		ps.setInt(1, periodId);
		ResultSet fs = ps.executeQuery();
		List<Student> students = new ArrayList<Student>();
		
		while (fs.next()) {
			students.add(new Student(fs.getInt(1), fs.getString(2), fs.getString(3)));
		}
		return students;
	}
	
	// 2. creates a new student
	public void createNewStudent(String firstName, String lastName, int periodId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_STUDENT_QUERY);
		ps.setString(1, firstName);
		ps.setString(2, lastName);
		ps.setInt(3, periodId);
		ps.executeUpdate();
	}

	//3. deletes all students from list by period id
	public void deleteStudentsByPeriodId(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_STUDENTS_BY_PERIOD_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	// 4. deletes specific student by student id
	public void deleteStudentsById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_STUDENT_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}

}
