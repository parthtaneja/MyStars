package Controllers;
import java.io.IOException;
import java.io.*;
import java.time.LocalTime;
import java.util.*;
import Models.Manager;
import Views.Driver;
import Models.Student;


public class studentAdd {
    public static void main(ArrayList students)  {
        String Save = "";
        Student ab;

        ab = (Student) students.get(students.size() - 1);
        Save += (ab.getStudentID()) + ',' + ab.getName() + ',' + (ab.getMatricNumber()) + ',' + ab.getPassword() + ',' + ab.getGender() + ',' + ab.getNationality();
        //System.out.println(Save);
        Boolean exists = false;
        try {
            String text;
            File file = new File("Students.txt");
            Scanner abc = new Scanner(file);
            while(abc.hasNextLine()) {
                text = abc.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");

                if(values[0].equals(ab.getStudentID())) {
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
            System.out.println("Another student with the same ID exists!");
        }
        else{
            try {
                File file = new File("Students.txt");
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
            System.out.println("New student added to system with student id: "+ ab.getStudentID());
            try {
                String text;
                File file = new File("Students.txt");
                Scanner abc = new Scanner(file);
                while(abc.hasNextLine()) {
                    text = abc.nextLine();
                    //System.out.println(text);
                    String[] values = text.split(",");
                    System.out.println("StudentID: " + values[0] + ", Name: " + values[1] );
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
