package entity;

public class Student {
	
	private int studentId;
	private String firstName;
	private String lastName;
	
	public Student(int studentId, String firstName, String lastName) {
		this.setStudentId(studentId);
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
