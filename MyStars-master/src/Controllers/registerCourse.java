package Controllers;
import java.io.IOException;
import java.io.*;
import java.time.LocalTime;
import java.util.*;
import Models.Manager;
import Views.Driver;
import Models.Student;
public class registerCourse {
    /**
     * Main function is to allow Students to register the courses that they choose
     * @param index
     */
    public static void main(String index)  {
        Set<String> hashSet = new HashSet<String>();
        Set<String> IndexhashSet = new HashSet<String>();
        System.out.println("Displaying all courses:");
        try {
            String text;
            File file = new File("Courses.txt");
            Scanner ab = new Scanner(file);

            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");
                hashSet.add(values[1]);
            }
            ab.close();
            System.out.println(hashSet);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        Scanner sc = new Scanner(System.in);
        String courseCode;
        System.out.print("Enter Course Code you want to add: ");
        courseCode = sc.nextLine();
        while(!hashSet.contains(courseCode)){
            System.out.print("Enter Course Code you want to add: ");
            courseCode = sc.nextLine();
        }

        String indexNumber;
        try {
            String text;
            File file = new File("Courses.txt");
            Scanner ab = new Scanner(file);

            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");
                if(values[1].equals(courseCode)){
                    IndexhashSet.add(values[2]);
                }
            }
            ab.close();
            //System.out.println(hashSet);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Displaying all index numbers:");
        System.out.println(IndexhashSet);
        System.out.println("Choose an index number:");
        indexNumber = sc.nextLine();

        while(!IndexhashSet.contains(indexNumber)){
            System.out.println("Choose an index number:");
            indexNumber = sc.nextLine();
        }

        String Save = indexNumber + ',' + index;
        try {
            File file = new File("RegisteredCourses.txt");
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter pr = new PrintWriter(br);
            pr.println(Save);
            pr.close();
            br.close();
            fr.close();
            //System.out.println("SUCCESS");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
