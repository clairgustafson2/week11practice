package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Period;

public class PeriodDao {
	
	private Connection connection;
	private StudentDao studentDao = new StudentDao();
	private final String GET_PERIOD_QUERY = "SELECT * FROM period";
	private final String GET_PERIOD_BY_ID_QUERY = "SELECT * FROM period WHERE id = ?";
	private final String CREATE_NEW_PERIOD_QUERY = "INSERT INTO period(name) VALUES(?)";
	private final String DELETE_PERIOD_BY_ID_QUERY = "DELETE FROM period WHERE id = ?";
	
	public PeriodDao() {
		connection = DBconnection.getConnection();
		
	}
	
	// 1. displays all periods
	public List <Period> getPeriods() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_PERIOD_QUERY).executeQuery();
		List<Period> periods = new ArrayList<Period>();
		
		while (rs.next()) {
			periods.add(populatePeriod(rs.getInt(1), rs.getString(2)));
		}
		
		return periods;
	}
	
	// 2. display period by id. only display that period and all students in that period
	public Period getPeriodById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_PERIOD_BY_ID_QUERY);
		ps.setInt(1,id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populatePeriod(rs.getInt(1), rs.getString(2));
		
	}
	
	// 3. creates period
	public void createNewPeriod(String periodName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_PERIOD_QUERY);
		ps.setString(1, periodName);
		ps.executeUpdate();
		
	}
	
	// 4. delete period by id
	public void deletePeriod(int id) throws SQLException {
		studentDao.deleteStudentsByPeriodId(id);
		PreparedStatement ps = connection.prepareStatement(DELETE_PERIOD_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	// 5. this populates periods
	private Period populatePeriod(int id, String name) throws SQLException {
		return new Period(id, name, studentDao.getStudentsByPeriodId(id)); 
		
	}

}
