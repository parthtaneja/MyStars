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
	 * Getter method for Student
	 * @param name
	 * @param matricNumber
	 * @param gender
	 * @param nationality
	 * @param password
	 * @return void
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
	 * Getter method for Student ID
	 * @return void
	 */
	public String getStudentID() {
		return studentID;
	}

	/**
	 * Getter method for Student NAME
	 * @return void
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter method for Student Matric No.
	 * @return void
	 */
	public String getMatricNumber() {
		return matricNumber;
	}

	/**
	 *Getter method for Student gender
	 * @return void
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Getter method for Student nationality
	 * @return void
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * Getter method for Student Courses
	 * @return void
	 */
	public ArrayList<Course> getCourses() {
		return courses;
	}

	/**
	 * Getter method for Student password
	 * @return void
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Getter method for adding each Course c for each student
	 * @param c
	 * @return void
	 */
	public void addCourse(Course c)
	{
		courses.add(c);
	}

	/**
	 * Display all courses for students
	 * @param
	 * @return void
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
	 * @param
	 * @return
	 */
	public int getCourseCount() 
	{
		return courses.size();
	}

	/**
	 *
	 * @param courseCode
	 * @return void
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
	 * @return void
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
	 * @return a string
	 */
	@Override
	public String toString() {
		return "StudentID: " + studentID + "\nName: " + name + "\nMatric Number: " + matricNumber + "\nGender: "
				+ gender + "\nNationality: " + nationality ;
	}
	
	
	
	
}
