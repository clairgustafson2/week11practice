package entity;

import java.util.List;

public class Period {
	
	private int periodId;
	private String name;
	private List<Student> students;
	
	public Period (int periodId, String name, List<Student> students) {
		this.setPeriodId(periodId);
		this.setName(name);
		this.setStudents(students);
		
	}

	public int getPeriodId() {
		return periodId;
	}

	public void setPeriodId(int periodId) {
		this.periodId = periodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	
}
