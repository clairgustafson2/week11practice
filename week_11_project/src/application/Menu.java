package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import entity.Period;
import entity.Student;
import dao.PeriodDao;
import dao.StudentDao;

public class Menu {
	
	private PeriodDao periodDao = new PeriodDao();
	private StudentDao studentDao = new StudentDao();
	private Scanner scanner= new Scanner(System.in);
	private List<String> options = Arrays.asList("Display Periods", "Display a Period", "Create Period", 
			"Delete Period", "Create Student", "Delete Student");
	
	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			
		try {
			if (selection.equals("1")) {
				displayPeriods();
			} else if (selection.equals("2")) {
				displayPeriod();
			} else if (selection.equals("3")) {
				createPeriod();
			} else if (selection.equals("4")) {
				deletePeriod();
			} else if (selection.equals("5")) {
				createStudent();
			} else if (selection.equals("6")) {
				deleteStudent();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
			
			System.out.println("Press enter to continue... ");
			scanner.nextLine();
			
		} while (!selection .equals("-1"));
	}
	private void printMenu() {
		System.out.println("Select an option\n -------------------------------------");
		for(int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}
	}
	
	private void displayPeriods() throws SQLException {
		List<Period> periods = periodDao.getPeriods();
		for(Period period : periods) {
			System.out.println(period.getPeriodId() + " : " + period.getName());
		}
	}

	private void displayPeriod() throws SQLException {
		System.out.print("Enter period id: ");
		int id = Integer.parseInt(scanner.nextLine());
		Period period = periodDao.getPeriodById(id);
		System.out.println(period.getPeriodId() + " : " + period.getName());
		for (Student student : period.getStudents()) {
			System.out.println("\tStudentId: " + student.getStudentId() + " - Name: " + student.getFirstName() + " " + student.getLastName() );
		}
	}
	
	private void createPeriod() throws SQLException {
		System.out.print("Enter new period name: ");
		String periodName = scanner.nextLine();
		periodDao.createNewPeriod(periodName);
	}
	
	private void deletePeriod() throws SQLException {
		System.out.print("Enter period id you want to delete: ");
		int id = Integer.parseInt(scanner.nextLine());
		periodDao.deletePeriod(id);
	}
	
	private void createStudent() throws SQLException {
		System.out.print("Enter first name of new student: ");
		String firstName = scanner.nextLine();
		System.out.print("Enter last name of new student: ");
		String lastName = scanner.nextLine();
		System.out.print("Enter period id for new student: ");
		int periodId = Integer.parseInt(scanner.nextLine());
		studentDao.createNewStudent(firstName, lastName, periodId);
	}
	
	private void deleteStudent() throws SQLException {
		System.out.print("Enter id of student you wish to delete: ");
		int id = Integer.parseInt(scanner.nextLine());
		studentDao.deleteStudentsById(id);
	}
}
