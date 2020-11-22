package Models;

public class Course {
	private String courseCode;
	private  String school;
	private int indexNumber;
	private int vacancy;
	
	public Course(String courseCode, String school, int indexNumber, int vacancy) {
		this.courseCode = courseCode;
		this.school = school;
		this.indexNumber = indexNumber;
		this.vacancy = vacancy;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public String getSchool() {
		return school;
	}

	public int getIndexNumber() {
		return indexNumber;
	}

	public int getVacancy() {
		return vacancy;
	}
	
	public void changeIndexNumber(int newIndexNumber)
	{
		this.indexNumber = newIndexNumber;
	}

	@Override
	public String toString() {
		return "Models.Course Code: " + courseCode + "\nSchool: " + school + "\nIndex Number: " + indexNumber + "\nVacancy: "
				+ vacancy ;
	}

}
