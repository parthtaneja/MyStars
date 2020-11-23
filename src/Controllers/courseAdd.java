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
    public static void main(ArrayList courses)  {
        String Save = "";
        Course ab;

        ab = (Course) courses.get(courses.size() - 1);
        Save += ab.getSchool() +  ',' + (ab.getCourseCode()) + ',' + (ab.getIndexNumber()) + ',' + ab.getVacancy() + ',' + ab.getday() + ',' + ab.getVenue() + ',' +  ab.getStartTime().toString() + ',' + ab.getEndTime().toString();
        //System.out.println(ab.getIndexNumber());

        Boolean exists = false;
        try {
            String text;
            File file = new File("Courses.txt");
            Scanner abc = new Scanner(file);
            while(abc.hasNextLine()) {
                text = abc.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");
                //System.out.println(values[2]);
                if(Integer.toString(ab.getIndexNumber()).equals(values[2])) {
                    //System.out.println("HERE IT HAPPENED");
                    exists = true;
                }
            }
            abc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        if(exists){
            System.out.println("Another course/index with the same ID exists!");
        }
        else{
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
            System.out.println("New course added to system with index id: "+ ab.getIndexNumber());
            try {
                String text;
                File file = new File("Courses.txt");
                Scanner abc = new Scanner(file);
                while(abc.hasNextLine()) {
                    text = abc.nextLine();
                    //System.out.println(text);
                    String[] values = text.split(",");
                    System.out.println("CourseCode: " + values[1] + ", IndexNumber: " + values[2] );
                }
                abc.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }


    }
}
