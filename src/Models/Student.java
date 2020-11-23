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
	private String email;
	
	public Student( String name, String matricNumber, String gender, String nationality, String password, String email) {
		this.studentID = matricNumber;
	    this.name = name;
		this.matricNumber = matricNumber;
		this.gender = gender;
		this.nationality = nationality;
		this.password = password;
		this.email = email;
		courses = new ArrayList<Course>();
	}

	public String getEmail() {return email;}
	public String getStudentID() {
		return studentID;
	}

	public String getName() {
		return name;
	}

	public String getMatricNumber() {
		return matricNumber;
	}

	public String getGender() {
		return gender;
	}

	public String getNationality() {
		return nationality;
	}
	
	
	public ArrayList<Course> getCourses() {
		return courses;
	}

	public String getPassword() {
		return password;
	}

	public void addCourse(Course c)
	{
		courses.add(c);
	}
	
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

	public int getCourseCount() 
	{
		return courses.size();
	}
	
	
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
	
	@Override
	public String toString() {
		return "StudentID: " + studentID + "\nName: " + name + "\nMatric Number: " + matricNumber + "\nGender: "
				+ gender + "\nNationality: " + nationality ;
	}
	
	
	
	
}
