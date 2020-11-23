package Views;
import java.io.IOException;
import java.io.*;
import java.time.LocalTime;
import java.util.*;
import Models.Manager;
import Views.Driver;
import Models.Student;
public class StudentMenu {
    public static void main(String index){
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            try
            {
                int choice;
                System.out.println("------------------------------------------");
                System.out.println("Welcome to Student Menu!");
                System.out.println("1. Add Course");
                System.out.println("2. Drop Course");
                System.out.println("3. Display Registered Course");
                System.out.println("4. Check Vacancies Available");
                System.out.println("5. Change index number of Course");
                System.out.println("6. Swap Index number with another Student");
                System.out.println("7. Logout");
                System.out.println("------------------------------------------");
                System.out.print("Your Option: ");
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                    {
                        Models.Manager.addCourse(index);
                        break;
                    }
                    case 2:
                    {
                        Models.Manager.dropCourse(index);
                        break;
                    }
                    case 3:
                    {
                        Models.Manager.displayRegisteredCourse(index);
                        break;
                    }
                    case 4:
                    {
                        Models.Manager.checkVacancies(index);
                        break;
                    }
                    case 5:
                    {
                        Models.Manager.changeIndex(index);
                        break;
                    }
                    case 6:
                    {
                        Models.Manager.swopIndex(index);
                        break;
                    }
                    case 7:
                    {
                        Views.Driver.main(null);
                    }

                    default:
                    {
                        System.out.println("Please Enter a Valid Option");
                    }
                }
            }
            catch(InputMismatchException | IOException ex)
            {
                System.out.println("Please enter a Integer");
            }


        }
    }
}
