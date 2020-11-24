package Views;
import java.io.IOException;
import java.io.*;
import java.time.LocalTime;
import java.util.*;
import Models.Manager;

public class studentLogin {
    /**
     * Allow the Student to login by checking the username and password of thelogin interface for student
     * @param manager
     */
    public static void main(Manager manager)  {
        Scanner sc = new Scanner(System.in);
        LocalTime time = LocalTime.now();
        //boolean studentTimeCheck =  manager.checkTime(time);
        boolean studentTimeCheck = true;
        if(studentTimeCheck)
        {
            String id;
            String password;
            System.out.print("Enter Id: ");
            id = sc.nextLine();
            System.out.print("Enter password: ");
            password = sc.nextLine();
            //int login = manager.validateStudent(id, password);


            try {
                String text;
                File file = new File("Students.txt");
                Scanner ab = new Scanner(file);
                while(ab.hasNextLine()) {
                    text = ab.nextLine();
                    //System.out.println(text);
                    String[] values = text.split(",");

                    if(values[0].equals(id)) {
                        //System.out.println("Found the ID");
                        if(values[3].equals(password)) {
                            System.out.println("Login successful!");
                            ab.close();
                            manager.studentMenu(values[0]);
                        }
                        else {
                            System.out.println("Incorrect password!");
                            ab.close();
                            Driver.main(null);
                        }
                    }
                }
                System.out.println("Admin with ID: " + id + " does not exist!");
                ab.close();
                Driver.main(null);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
        else
        {
            System.out.println("Try Login in  student times");
            Driver.main(null);
        }

    }
}
