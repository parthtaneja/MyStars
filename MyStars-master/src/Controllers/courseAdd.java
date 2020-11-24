package Controllers;
import java.io.IOException;
import java.io.*;
import java.time.LocalTime;
import java.util.*;
import Models.Course;
import Models.Manager;
import Views.Driver;
import Models.Student;

public class courseAdd {
    /**
     * Main function to add courses to courses.txt in the form of a string
     * @param courses
     */
    public static void main(ArrayList courses)  {
        String Save = "";
        Course ab;

        ab = (Course) courses.get(courses.size() - 1);
        Save += ab.getSchool() +  ',' + (ab.getCourseCode()) + ',' + (ab.getIndexNumber()) + ',' + ab.getVacancy();
        //System.out.println(Save);
        try {
            File file = new File("Courses.txt");
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
