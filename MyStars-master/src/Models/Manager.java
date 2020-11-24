package Models;
import Views.Driver;
import java.io.IOException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Manager {
	
	private static Manager instance = null;
	private LocalTime startTime;
	private LocalTime endTime;
	private ArrayList<Student> studentList;
	private ArrayList<Course> courseList;

	/**
	 * Manager private instance
	 */
	private Manager()
	{
		studentList = new ArrayList<Student>();
		courseList = new ArrayList<Course>();
		startTime = LocalTime.of(10,0,0);
		endTime = LocalTime.of(18,0,0                  );
		
	}

	/**
	 * Gets the instance of the Manager Class if null then creates a new instance
	 * @return instance
	 */
	public static Manager getInstance() 
	{
		if(instance == null) 
		{
			instance = new Manager();
		}
		return instance;
	}

	/**
	 * Checks the local time and parses it
	 * @param time
	 * @return boolean true or false if time is in the range
	 */
	public boolean checkTime(LocalTime time) 
	{
		try {
			String text;
			File file = new File("Timing.txt");
			Scanner ab = new Scanner(file);
			while(ab.hasNextLine()) {
				text = ab.nextLine();
				//System.out.println(text);
				String[] values = text.split(",");
				this.startTime = LocalTime.parse(values[0]);
				this.endTime = LocalTime.parse(values[1]);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		if(time.isAfter(startTime) && time.isBefore(endTime))
		{
			return true;
		}
		return false;
	}

	/**
	 * Calls the registerCourse class through the addCourse function
	 * @param index
	 */
	public static void addCourse(String index)
	{
		Controllers.registerCourse.main(index);

	}

	/**
	 * Calls the deregisterCourse class through the dropCourse function
	 * @param index
	 * @throws IOException
	 */
	public static void dropCourse(String index) throws IOException {
		Controllers.deregisterCourse.main(index);

	}

	/**
	 * Calls the registeredCourse class through the function
	 * @param index
	 */
	public static void displayRegisteredCourse(String index)
	{
		Views.registeredCourses.main(index);

	}

	/**
	 * Calls the checkvacancy class through the function
	 * @param index
	 */
	public static void checkVacancies(String index)
	{
		Controllers.checkVacancy.main(index);
		
	}

	/**
	 * Calls the changeIndex class through the function
	 * @param index
	 * @throws IOException
	 */
	public static void changeIndex(String index) throws IOException {
		Controllers.changeIndex.main(index);
	}

	/**
	 * Calls the studentMenu class through the function
	 * @param index
	 */
	public void studentMenu(String index)
	{
		Views.StudentMenu.main(index);
	}

	/**
	 * Calls the swopIndex class through the function
	 * @param index
	 * @throws IOException
	 */
	public static void swopIndex(String index) throws IOException {
		Controllers.swopIndex.main(index);

	}

	/**
	 * Displays the Admin Menu
	 */
	public void AdminMenu() 
	{
		while(true)
		{
			Scanner sc = new Scanner(System.in);
			int choice;
			try
			{

				System.out.println("Welcome To Admin Menu");
				System.out.println("1. Edit student acesss period");
				System.out.println("2. Add a student");
				System.out.println("3. Add a course");
				System.out.println("4. Update a course");
				System.out.println("5. Check available slot for an index number");
				System.out.println("6. Print student list By index number");
				System.out.println("7. Logout");
				System.out.print("Your Choice: ");
				choice = sc.nextInt();
				switch (choice)
				{
					case 1:
					{
						editStudentPeriod();
						break;
					}
					case 2:
					{
						addStudent();
						break;
					}
					case 3:
					{
						addCourse();
						break;
					}
					case 4:
					{
						updateCourse();
						break;
					}
					case 5:
					{
						checkSlot();
						break;
					}
					case 6:
					{
						printStudentList();
						break;
					}
					case 7:
					{
						Views.Driver.main(null);
					}
					default:
					{
						System.out.println("Enter a Valid Option");
					}
				}

			}
			catch(InputMismatchException | IOException e)
			{
				System.out.println("Please Enter a Integer");
				sc.nextLine();
			}

		}
	}

	/**
	 * private instance to add a student to the STARS application
	 */
	private void addStudent()
	{
		String name; 
		String matricNumber;
		String gender; 
		String nationality; 
		String password;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Student Information");
		System.out.print("Name: ");
		name = sc.nextLine();
		System.out.print("Nationality: ");
		nationality = sc.nextLine();
		System.out.print("Gender: ");
		gender= sc.nextLine();
		System.out.print("Password: ");
		password = sc.nextLine();
		System.out.print("Matric Number: ");
		matricNumber = sc.nextLine();
		Student student = new Student(name, matricNumber, gender, nationality, password);
		studentList.add(student);
		System.out.println("New Models.Student Add To System With Models.Student Id: "+student.getStudentID());
		Controllers.studentAdd.main(studentList);
		
	}

	/**
	 * private instance to add a student to the STARS application
	 */
	private void addCourse()
	{
		String courseCode;
		String school; 
		int indexNumber; 
		int vacancy;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Course Information");
		System.out.print("School: ");
		school = sc.nextLine();
		System.out.print("Course Code: ");
		courseCode = sc.nextLine();
		System.out.print("Index Number: ");
		indexNumber = sc.nextInt();
	    System.out.print("Vacancy: ");
	    vacancy = sc.nextInt();
	    Course course = new Course(courseCode, school, indexNumber, vacancy);
	    courseList.add(course);
	    System.out.println("New Models.Course Added To System");
		Controllers.courseAdd.main(courseList);
	}

	/**
	 * private instance to update a course in the STARS application
	 * @throws IOException
	 */
	public static void updateCourse() throws IOException {
		Controllers.updateCourse.main(null);
	}

	/**
	 * private instance to checkslot in the STARS application
	 */
	public static void checkSlot()
	{
		Scanner sc = new Scanner(System.in);
		int indexNumber;
		System.out.print("Enter index number: ");
		indexNumber = sc.nextInt();
		try {
			String text;
			File file = new File("Courses.txt");
			Scanner ab = new Scanner(file);
			Integer a = 0;
			while(ab.hasNextLine()) {
				text = ab.nextLine();
				//System.out.println(text);
				String[] values = text.split(",");
				//System.out.println(values[2]);
				//System.out.println(indexNumber);
				if(Integer.toString(indexNumber).equals(values[2]))  {
					System.out.println("Vacancy of " + indexNumber + ": " + values[3]);
					a = 1;
				}
			}
			ab.close();
			if (a == 0){
				System.out.println("Index Number not found.");
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	}

	/**
	 * private instance printstudentlist to call the printStudents class in the STARS application
	 */
	private void printStudentList()
	{
		Controllers.printStudents.main(null);
	}

	/**
	 * private instance to edit a student to the STARS application
	 */
	private void editStudentPeriod()
	{
		Scanner sc = new Scanner(System.in);
	    int start, end;
	    System.out.print("Enter start value for student time (hour): ");
	    start = sc.nextInt();
	    while(start < 0 || start > 24){
			System.out.print("Enter start value for student time (hour): ");
			start = sc.nextInt();
		}
		System.out.print("Enter end value for student time (hour): ");
		end = sc.nextInt();
		while(end < 0 || end > 24 || end < start){
			System.out.print("Enter end value for student time (hour): ");
			end = sc.nextInt();
		}
		this.startTime = LocalTime.of(start, 0, 0);
		this.endTime = LocalTime.of(end, 0, 0);
		System.out.println("Time for student updated");
		String Save = this.startTime.toString() + ',' + this.endTime.toString();
		try {
			File file = new File("Timing.txt");
			FileWriter fr = new FileWriter(file, false);
			BufferedWriter br = new BufferedWriter(fr);
			PrintWriter pr = new PrintWriter(br);
			pr.println(Save);
			pr.close();
			br.close();
			fr.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}