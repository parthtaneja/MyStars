package Models;

public class Course {
	private String courseCode;
	private  String school;
	private int indexNumber;
	private int vacancy;

	/**
	 * Course getter Class, Houses all the getter functions of the class Course
	 * @param courseCode
	 * @param school
	 * @param indexNumber
	 * @param vacancy
	 * @return void
	 */
	public Course(String courseCode, String school, int indexNumber, int vacancy) {
		this.courseCode = courseCode;
		this.school = school;
		this.indexNumber = indexNumber;
		this.vacancy = vacancy;
	}

	/**
	 * Gets Course Code
	 * @param
	 * @return courseCode
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * Gets School
	 * @param
	 * @return school
	 */
	public String getSchool() {
		return school;
	}

	/**
	 * get the index number
	 * @param
	 * @return the index number
	 */
	public int getIndexNumber() {
		return indexNumber;
	}

	/**
	 * get the vacancy
	 * @param
	 * @return vacancy
	 */
	public int getVacancy() {
		return vacancy;
	}

	/**
	 * change the index number
	 * @param newIndexNumber
	 * @return None
	 */
	public void changeIndexNumber(int newIndexNumber)
	{
		this.indexNumber = newIndexNumber;
	}

	/**
	 * Overriding method of toString where we can return a String of the Course
	 * @param
	 * @return
	 */
	@Override
	public String toString() {
		return "Models.Course Code: " + courseCode + "\nSchool: " + school + "\nIndex Number: " + indexNumber + "\nVacancy: "
				+ vacancy ;
	}

}
