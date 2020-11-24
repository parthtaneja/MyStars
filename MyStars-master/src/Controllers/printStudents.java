package Controllers;
import java.io.*;
import java.time.LocalTime;
import java.util.*;
import Models.Manager;
import Views.Driver;
import Models.Student;
public class printStudents {
    /**
     * Main function to printStudents for the Admin from Students.txt and Registered Courses.txt
     * @param args
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
                hashSet.add(values[2]);
            }
            ab.close();

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("All possible index numbers:");
        System.out.println(hashSet);

        String indexNumber;
        System.out.println("Choose index number: ");
        Scanner sc = new Scanner(System.in);
        indexNumber = sc.nextLine();
        while(!hashSet.contains(indexNumber)){
            System.out.println("Choose index number: ");
            indexNumber = sc.nextLine();
        }

        Set<String> studentSet = new HashSet<String>();
        try {
            String text;
            File file = new File("RegisteredCourses.txt");
            Scanner ab = new Scanner(file);
            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");
                if(values[0].equals(indexNumber)){
                    studentSet.add(values[1]);
                }
            }
            ab.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("All student ids registered:");
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
