package Controllers;

import java.io.*;
import java.time.LocalTime;
import java.util.*;
import Models.Manager;
import Views.Driver;
import Models.Student;
public class printCourse {
    /**
     * main function
     * To print the course for all student taking a specific course/index
     * @param args string
     * @return void
     */
    public static void main(String args[]){
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

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("All possible Course numbers:");
        System.out.println(hashSet);

        String CourseNumber;
        System.out.println("Choose Course number: ");
        Scanner sc = new Scanner(System.in);
        CourseNumber = sc.nextLine();
        while(!hashSet.contains(CourseNumber)){
            System.out.println("Choose index number: ");
            CourseNumber = sc.nextLine();
        }

        Set<String> studentSet = new HashSet<String>();
        try {
            String text;
            File file = new File("Coursing.txt");
            Scanner ab = new Scanner(file);
            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");
                if(values[2].equals(CourseNumber)){
                    studentSet.add(values[1]);
                }
            }
            ab.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        //System.out.println("All student ids registered:");
        //System.out.println(studentSet);

        try {
            String text;
            File file = new File("Students.txt");
            Scanner ab = new Scanner(file);
            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");
                if(studentSet.contains(values[0])){
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
