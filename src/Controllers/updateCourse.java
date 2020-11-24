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
import java.time.LocalTime;
public class updateCourse {
    public static void main(String agrs[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        String indexNumber;
        System.out.print("Enter index number for the course to be updated: ");
        indexNumber = sc.nextLine();
        LocalTime startTime;
        LocalTime endTime;
        String newCourseCode;
        String newschool;
        int newindexNumber;
        int newvacancy;
        String day;
        String venue;
        int start;
        int end;
        // NEED TO ADD SOME THINGS HERE
        System.out.println("Enter Information for Course Again");
        System.out.print("School: ");
        newschool = sc.nextLine();
        System.out.print("Course Code: ");
        newCourseCode = sc.nextLine();
        System.out.print("Day: ");
        day = sc.nextLine();
        System.out.print("Venue: ");
        venue = sc.nextLine();
        System.out.print("Start time (Hour): ");
        start = sc.nextInt();
        System.out.print("End time (Hour): ");
        end = sc.nextInt();
        System.out.print("Vacancy: ");
        newvacancy = sc.nextInt();
        startTime = LocalTime.of(start, 0, 0);
        endTime = LocalTime.of(end, 0, 0);

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

            if(values[2].equals(indexNumber)) {
                //System.out.println("Found the ID");
                String Save = newschool + ',' + newCourseCode + ',' + indexNumber + ',' + newvacancy + ',' + day + ',' + venue + ',' + startTime.toString() + ',' + endTime.toString();
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
