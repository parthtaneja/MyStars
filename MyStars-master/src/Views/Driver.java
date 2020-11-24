package Views;

import Models.Manager;

import java.util.InputMismatchException;
import java.util.Scanner;
public class Driver {

	/**
	 * Main Class to Drive the entire STARS Application
	 * @param agrs
	 */
   public static void main(String agrs[]) {
	  
	   Manager manager = Manager.getInstance();
	   Scanner sc = new Scanner(System.in); 
	   while (true) {
		try 
		{
			int choice;
			System.out.println("Welcome ");
			System.out.println("1. To login as Admin");
			System.out.println("2. to login as Student");
			System.out.println("3. To Exit");
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
		catch(InputMismatchException ex) 
		{
			System.out.println("Enter a valid integer values");
			sc.next();
		}
		
	   }
	  
	 
  }
	
}
