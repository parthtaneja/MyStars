package Controllers;
import java.io.*;
import java.time.LocalTime;
import java.util.*;
import Models.Manager;
import Views.Driver;
import Models.Student;
public class displayStudentsByCourse {
    public static void main(String args[]){
        System.out.println("Displaying all courses");
        Set<String> hashSet = new HashSet<String>();

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
        System.out.println("Enter course code: ");
        courseCode = sc.nextLine();
        while(!hashSet.contains(courseCode)){
            System.out.println("Enter course code: ");
            courseCode = sc.nextLine();
        }

        Set<String> allIndexnumbers = new HashSet<String>();
        try {
            String text;
            File file = new File("Courses.txt");
            Scanner ab = new Scanner(file);

            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");
                if(values[1].equals(courseCode)){
                    allIndexnumbers.add(values[2]);
                }
            }
            ab.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        Set<String> allStudentIds = new HashSet<String>();
        try {
            String text;
            File file = new File("RegisteredCourses.txt");
            Scanner ab = new Scanner(file);

            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");
                if(allIndexnumbers.contains(values[0])){
                    allStudentIds.add(values[1]);
                }
            }
            ab.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("Displaying all students in the course: ");
        try {
            String text;
            File file = new File("Students.txt");
            Scanner ab = new Scanner(file);

            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");
                if(allStudentIds.contains(values[0])){
                    System.out.println("Name: " + values[1] + ", Gender: " + values[4] + ", Nationality: " + values[5]);
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
