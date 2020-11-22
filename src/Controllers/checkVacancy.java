package Controllers;
import java.io.IOException;
import java.io.*;
import java.time.LocalTime;
import java.util.*;
import Models.Manager;
import Views.Driver;
import Models.Student;
public class checkVacancy {
    public static void main(String index){
        Scanner sc = new Scanner(System.in);
        String courseCode;


        Set<String> IndexhashSet = new HashSet<String>();
        Set<String> hashSet = new HashSet<String>();
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
        System.out.println("Enter the course code: ");
        courseCode = sc.nextLine();
        while(!hashSet.contains(courseCode)){
            System.out.println("Enter the course code: ");
            courseCode = sc.nextLine();
        }
        System.out.println("Displaying all index numbers:");
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
            System.out.println(IndexhashSet);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        String indexNumber;
        System.out.println("Choose an index number:");
        indexNumber = sc.nextLine();
        while(!IndexhashSet.contains(indexNumber)){
            System.out.println("Choose an index number:");
            indexNumber = sc.nextLine();
        }

        try {
            String text;
            File file = new File("Courses.txt");
            Scanner ab = new Scanner(file);
            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");
                if(values[2].equals(indexNumber)) {
                    System.out.println("Vacancy: " + values[3]);
                }
            }
            ab.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
