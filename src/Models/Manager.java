package Models;
import Controllers.Login;
import Controllers.SendEmail;
import Controllers.studentAdd;
import Views.Driver;
import java.io.IOException;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	
	public static void addCourse(String index)
	{
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

	private void addStudent()
	{
		String name="";
		String matricNumber="";
		String gender="";
		String nationality="";
		String password="";
		boolean notrue = true;
		ArrayList<String> tokening = new ArrayList<String>();

		try {

			String text;
			int count=0;
			BufferedReader in = new BufferedReader(new FileReader("Students.txt"));
			//File file = new File("Students.txt");
			//Scanner ab = new Scanner(file);
			String str;
			while ((text = in.readLine()) != null) {
				//text = ab.nextLine();
				//System.out.println(text);
				String[] values = text.split(",");
				//System.out.println(count);
				tokening.add(values[0]);
				count++;
				//System.out.println(tokening);
				//in.close();


			}
		}

		catch (Exception e) {
			System.out.println("File Read Error students1");
		}
		while(notrue) {
			int count = 0;
			Scanner sc = new Scanner(System.in);

			Console console = System.console();
			if (console != null) {
				char[] passwordArray = console.readPassword("Enter password (it will be invisible): ");
				String st1 = new String(passwordArray);

				System.out.println(st1);
				password = st1;
			}

			System.out.println("Enter Student Information");

			System.out.print("Matric Number: ");
			matricNumber = sc.nextLine();

			for (String toks : tokening) {
				//System.out.println(toks);
				if (toks.equals(matricNumber)) {
					System.out.println("Matric Number SAME!!!!");
					notrue = true;
					count++;
				}


			}

			if (count > 0) {
				notrue = true;
				continue;
			}


			System.out.print("Name: ");
			name = sc.nextLine();
			System.out.print("Nationality: ");
			nationality = sc.nextLine();
			System.out.print("Gender: ");
			gender = sc.nextLine();
			System.out.print("Password: ");
			password = sc.nextLine();
			System.out.print("Email: ");
			String Email = sc.nextLine();
			String text = "Sending Notifications";

			SendEmail email = new SendEmail();
			email.Send(Email,text);

			notrue=false;
			//System.out.println(studentList);

		}



		createHash(matricNumber,password);



		Student student = new Student(name, matricNumber, gender, nationality, password);
		studentList.add(student);
		System.out.println("New Models.Student Add To System With Models.Student Id: "+student.getStudentID());
		studentAdd.main(studentList);
		
	}
	
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


		/*
		public Boolean loginUser(String uname) {

			Scanner sc = new Scanner(System.in); // System.in is a standard input stream
			//System.out.print("Enter user name: ");
			String str = uname; // reads string
			str
			Console console = System.console();
			char[] passwordArray = console.readPassword("Enter password (it will be invisible): ");
			String st1 = new String(passwordArray);
			return testKey(str, st1);
		}*/

		public Boolean loginStudent() {

			Scanner sc = new Scanner(System.in); // System.in is a standard input stream
			System.out.print("Enter user name: ");
			String str = sc.nextLine(); // reads string

			Console console = System.console();
			char[] passwordArray = console.readPassword("Enter password (it will be invisible): ");
			String st1 = new String(passwordArray);
			return testKeys(str, st1);
		}

		public byte[] getSHA(String input) throws NoSuchAlgorithmException {
			// Static getInstance method is called with hashing SHA
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			// digest() method called
			// to calculate message digest of an input
			// and return array of byte
			return md.digest(input.getBytes(StandardCharsets.UTF_8));
		}

		public String toHexString(byte[] hash) {
			// Convert byte array into signum representation
			BigInteger number = new BigInteger(1, hash);

			// Convert message digest into hex value
			StringBuilder hexString = new StringBuilder(number.toString(16));

			// Pad with leading zeros
			while (hexString.length() < 32) {
				hexString.insert(0, '0');
			}

			return hexString.toString();
		}

		public String generate(String s1) {
			try {
				s1 = toHexString(getSHA(s1));
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Exception thrown for incorrect hash algorithm: " + e);
			}
			return s1;
		}

		public Boolean admintestKey(String uname, String upass) {

			String userNameHash = generate(uname);
			String passwordHash = generate(upass);

			try {

				FileReader fr = new FileReader("AdminPass.txt");
				BufferedReader in = new BufferedReader(fr);
				String hash1, hash2;
				String str;
				while ((str = in.readLine()) != null) {
					//System.out.println(str);
					// splitting lines on the basis of token
					String[] tokens = str.split(",");
					hash1 = tokens[0];
					//System.out.println(str);
					hash2 = tokens[1];
					if (hash1.equals(userNameHash) && hash2.equals(passwordHash))
						return true;


				}
			}

			catch (Exception e) {
				System.out.println("File Read Error login");
			}

			return false;
		}



		public void createHash (String uname, String upass){

			String userNameHash = generate(uname);
			String passwordHash = generate(upass);
			try {
				var myWriter = new FileWriter("passwords.txt",true);
				String line = "";
				line = userNameHash + "," + passwordHash;
				myWriter.write(System.lineSeparator());
				System.out.println(line);
				myWriter.write(line);
				myWriter.close();
			} catch (IOException e) {
				System.out.println("An error occurred. pass");
			}

		}
		public Boolean testKeys(String uname, String upass) {

			String userNameHash = generate(uname);
			String passwordHash = generate(upass);
			//FileReader fr;

			//System.out.println((userNameHash+passwordHash));

			try {
				FileReader fr = new FileReader("passwords.txt");
				BufferedReader in = new BufferedReader(fr);
				String hash1, hash2;
				//String str1 = in.readLine();
				String str;
				while ((str = in.readLine()) != null) {
					//System.out.println(str);
					// splitting lines on the basis of token
					String[] tokens = str.split(",");
					hash1 = tokens[0];
					//System.out.println(str);
					hash2 = tokens[1];
					if (hash1.equals(userNameHash) && hash2.equals(passwordHash))
						return true;

				}

			} catch (Exception e) {
				System.out.println("dog");

			}

			//System.out.println();
			return false;
		}




}