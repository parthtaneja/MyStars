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
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Manager {
	
	private static Manager instance = null;
	private LocalTime startTime;
	private LocalTime endTime;
	private ArrayList<Student> studentList;
	private ArrayList<Course> courseList;
	private Manager() 
	{
		studentList = new ArrayList<Student>();
		courseList = new ArrayList<Course>();
		startTime = LocalTime.of(10,0,0);
		endTime = LocalTime.of(18,0,0                  );
		
	}
	
	public static Manager getInstance() 
	{
		if(instance == null) 
		{
			instance = new Manager();
		}
		return instance;
	}

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
	
	public static void addCourse(String index) throws IOException {
		Controllers.registerCourse.main(index);

	}

	public static void dropCourse(String index) throws IOException {
		Controllers.deregisterCourse.main(index);

	}

	public static void displayRegisteredCourse(String index)
	{
		Views.registeredCourses.main(index);

	}
	
	public static void checkVacancies(String index)
	{
		Controllers.checkVacancy.main(index);
		
	}
	
	public static void changeIndex(String index) throws IOException {
		Controllers.changeIndex.main(index);
	}
	
	public void studentMenu(String index)
	{
		Views.StudentMenu.main(index);
	}

	public static void swopIndex(String index) throws IOException {
		Controllers.swopIndex.main(index);

	}

	public void AdminMenu() 
	{
		while(true)
		{
			Scanner sc = new Scanner(System.in);
			int choice;
			try
			{
				System.out.println("------------------------------------------");
				System.out.println("Welcome To Admin Menu");
				System.out.println("1. Edit student acesss period");
				System.out.println("2. Add a student");
				System.out.println("3. Add a course");
				System.out.println("4. Update a course");
				System.out.println("5. Check available slot for an index number");
				System.out.println("6. Print student list By index number");
				System.out.println("7. Print student list By course code");
				System.out.println("8. Logout");
				System.out.println("------------------------------------------");
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
						printStudentbyCourse();
						break;
					}
					case 8:
					{
						Views.Driver.main(null);
					}
					default:
					{
						System.out.println("Enter a Valid Option");
					}
				}

			}
			catch(InputMismatchException | IOException | NoSuchAlgorithmException e)
			{
				System.out.println("Please Enter a Integer");
				sc.nextLine();
			}

		}
	}

	private void printStudentbyCourse() {
		Controllers.displayStudentsByCourse.main(null);

	}


	public static byte[] getSHA(String input) throws NoSuchAlgorithmException
	{
		// Static getInstance method is called with hashing SHA
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		// digest() method called
		// to calculate message digest of an input
		// and return array of byte
		return md.digest(input.getBytes(StandardCharsets.UTF_8));
	}

	public static String toHexString(byte[] hash)
	{
		// Convert byte array into signum representation
		BigInteger number = new BigInteger(1, hash);

		// Convert message digest into hex value
		StringBuilder hexString = new StringBuilder(number.toString(16));

		// Pad with leading zeros
		while (hexString.length() < 32)
		{
			hexString.insert(0, '0');
		}

		return hexString.toString();
	}


	private void addStudent() throws NoSuchAlgorithmException {
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
		String hashedPwd = toHexString(getSHA(password));
		System.out.println(hashedPwd);
		Student student = new Student(name, matricNumber, gender, nationality, hashedPwd);
		studentList.add(student);
		Controllers.studentAdd.main(studentList);
		
	}
	
	private void addCourse()
	{
		String courseCode;
		String school; 
		int indexNumber; 
		int vacancy;
		String venue;
		String day;
		int Start;
		int End;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Course Information");
		System.out.print("School: ");
		school = sc.nextLine();
		System.out.print("Course Code: ");
		courseCode = sc.nextLine();
		System.out.print("Venue: ");
		venue = sc.nextLine();
		System.out.print("Day: ");
		day = sc.nextLine();
		System.out.print("Index Number: ");
		indexNumber = sc.nextInt();
	    System.out.print("Vacancy: ");
	    vacancy = sc.nextInt();
		System.out.print("Start Time (Hour) : ");
		Start = sc.nextInt();
		System.out.print("End Time (Hour): ");
		End = sc.nextInt();
	    Course course = new Course(courseCode, school, indexNumber, vacancy, day, venue, Start, End);
	    courseList.add(course);
	    //System.out.println("New Models.Course Added To System");
		Controllers.courseAdd.main(courseList);
	}

	public static void updateCourse() throws IOException {
		Controllers.updateCourse.main(null);
	}

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

	private void printStudentList()
	{
		Controllers.printStudents.main(null);
	}

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