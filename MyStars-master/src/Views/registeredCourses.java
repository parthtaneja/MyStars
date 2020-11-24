package Views;
import java.io.IOException;
import java.io.*;
import java.time.LocalTime;
import java.util.*;
import Models.Manager;
import Views.Driver;
import Models.Student;

public class registeredCourses {
    /**
     * Finding all Registered Courses
     * @param index
     */
    public static void main(String index)  {
        Set<String> hashSet = new HashSet<String>();
        System.out.println("Displaying all index numbers registered:");

        try {
            String text;
            File file = new File("RegisteredCourses.txt");
            Scanner ab = new Scanner(file);
            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");

                if(values[1].equals(index)) {
                    hashSet.add(values[0]);
                }
            }

            ab.close();
            System.out.println(hashSet);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

    }
}
