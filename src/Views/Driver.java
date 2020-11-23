package Views;

import Models.Manager;

import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Driver {
	
   public static void main(String agrs[]) {
	   System.out.println("Welcome! Login Menu:");
	   Manager manager = Manager.getInstance();
	   Scanner sc = new Scanner(System.in); 
	   while (true) {
		try 
		{
			int choice;
			System.out.println("------------------------------------------");
			System.out.println("1. Login as Admin");
			System.out.println("2. Login as Student");
			System.out.println("3. Exit");
			System.out.println("------------------------------------------");
			System.out.print("Your Choice: ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
			{
				adminLogin.main(manager);
				break;
			}
			case 2:
			{
				studentLogin.main(manager);
				break;
			}
			case 3:
			{
				System.out.println("Exiting the code");
				System.exit(0);
			}
			default:
			{
				System.out.println("Invalid option");
			}
			}
		}
		catch(InputMismatchException | NoSuchAlgorithmException ex)
		{
			System.out.println("Enter a valid integer values");
			sc.next();
		}
		
	   }
	  
	 
  }
	
}
