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
        try {
            File file = new File("Students.txt");
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter pr = new PrintWriter(br);
            pr.println(Save);
            pr.close();
            br.close();
            fr.close();
            System.out.println("SUCCESS");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
