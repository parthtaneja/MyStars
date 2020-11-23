package Models;

import Models.Course;

import java.util.ArrayList;

public class Student {

	private String studentID;
	private String name;
	private String matricNumber;
	private String gender;
	private String nationality;
	private String password;
	private ArrayList<Course> courses;

	/**
	 *
	 * @param name
	 * @param matricNumber
	 * @param gender
	 * @param nationality
	 * @param password
	 */
	public Student( String name, String matricNumber, String gender, String nationality, String password) {
		this.studentID = matricNumber;
	    this.name = name;
		this.matricNumber = matricNumber;
		this.gender = gender;
		this.nationality = nationality;
		this.password = password;
		courses = new ArrayList<Course>();
	}

	/**
	 *
	 * @return
	 */
	public String getStudentID() {
		return studentID;
	}

	/**
	 *
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 *
	 * @return
	 */
	public String getMatricNumber() {
		return matricNumber;
	}

	/**
	 *
	 * @return
	 */
	public String getGender() {
		return gender;
	}

	/**
	 *
	 * @return
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 *
	 * @return
	 */
	public ArrayList<Course> getCourses() {
		return courses;
	}

	/**
	 *
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 *
	 * @param c
	 */
	public void addCourse(Course c)
	{
		courses.add(c);
	}

	/**
	 *
	 */
	public void displayAllCourses() 
	{
		if(courses.size() == 0) 
		{
			System.out.println("No course registered for this student");
		}
		else 
		{
			System.out.println("Courses information");
			for(int i =0 ;i < courses.size();i++)
			{
				System.out.println(courses.get(i).toString());
			}
		}
		
	}

	/**
	 *
	 * @return
	 */
	public int getCourseCount() 
	{
		return courses.size();
	}

	/**
	 *
	 * @param courseCode
	 */
	public void dropCourse(String courseCode)
	{
		for(int i =0; i<courses.size();i++)
		{
			if(courses.get(i).getCourseCode() == courseCode) 
			{
				courses.remove(i);
			}
		}
	}

	/**
	 *
	 * @param courseCode
	 * @return
	 */
	public boolean checkCourseCode(String courseCode)
	{
		for(int i =0; i<courses.size();i++)
		{
			if(courses.get(i).getCourseCode() == courseCode) 
			{
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * @param indexNumber
	 * @param newIndexNumber
	 */
	public void changeIndex(int indexNumber, int newIndexNumber) 
	{
		for(int i =0; i<courses.size();i++)
		{
			if(courses.get(i).getIndexNumber() == indexNumber) 
			{
				courses.get(i).changeIndexNumber(newIndexNumber);
			}
		}
	}

	/**
	 *
	 * @param indexNumber
	 * @return
	 */
	public boolean checkCourseIndex(int indexNumber)
	{
		for(int i =0; i<courses.size();i++)
		{
			if(courses.get(i).getIndexNumber() == indexNumber) 
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return "StudentID: " + studentID + "\nName: " + name + "\nMatric Number: " + matricNumber + "\nGender: "
				+ gender + "\nNationality: " + nationality ;
	}
	
	
	
	
}
