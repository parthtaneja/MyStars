package Controllers;
import java.io.IOException;
import java.io.*;
import java.time.LocalTime;
import java.util.*;
import Models.Manager;
import Views.Driver;
import Models.Student;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class updateCourse {
    public static void main(String agrs[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        String courseCode;
        System.out.print("Enter course code for the course to be updated: ");
        courseCode = sc.nextLine();

        String newCourseCode;
        String newschool;
        int newindexNumber;
        int newvacancy;
        System.out.println("Enter Information for Course Again");
        System.out.print("School: ");
        newschool = sc.nextLine();
        System.out.print("Course Code: ");
        newCourseCode = sc.nextLine();
        System.out.print("Index Number: ");
        newindexNumber = sc.nextInt();
        System.out.print("Vacancy: ");
        newvacancy = sc.nextInt();

        File file = new File("CoursesTemp.txt");
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        PrintWriter pr = new PrintWriter(br);

        File rfile = new File("Courses.txt");
        Scanner ab = new Scanner(rfile);
        String text;
        while(ab.hasNextLine()) {
            text = ab.nextLine();
            //System.out.println(text);
            String[] values = text.split(",");

            if(values[1].equals(courseCode)) {
                //System.out.println("Found the ID");
                String Save = newschool + ',' + newCourseCode + ',' + newindexNumber + ',' + newvacancy;
                pr.println(Save);
            }
            else{
                pr.println(text);
            }
        }
        ab.close();
        pr.close();
        br.close();
        fr.close();

        Files.deleteIfExists(Paths.get("Courses.txt"));
        Path source = Paths.get("CoursesTemp.txt");
        Files.move(source, source.resolveSibling("Courses.txt"));
    }
}
