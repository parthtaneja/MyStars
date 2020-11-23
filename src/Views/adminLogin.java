package Views;
import java.io.IOException;
import java.io.*;
import java.util.*;
import Models.Manager;

public class adminLogin {
    /**
     * Checks whether the Admin can log in with the correct username & password
     * @param manager
     * @return void
     */
    public static void main(Manager manager)  {
        Scanner sc = new Scanner(System.in);
        String id;
        String password;
        System.out.print("Enter Id: ");
        id = sc.nextLine();
        System.out.print("Enter password: ");
        password = sc.nextLine();
        try {
            String text;
            File file = new File("Admin.txt");
            Scanner ab = new Scanner(file);
            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");

                if(manager.admintestKey(id,password)){
                    System.out.println("YEAH baby yeah");
                }

                if(values[0].equals(id)) {
                    if(values[1].equals(password)) {
                        System.out.println("Login successful!");
                        ab.close();
                        manager.AdminMenu();
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
}