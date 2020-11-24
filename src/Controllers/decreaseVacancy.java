package Controllers;
import java.io.IOException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.*;
import Models.Manager;
import Views.Driver;
import Models.Student;
public class decreaseVacancy {
    public static void main(String indexNumber) throws IOException {
        try {
            File file = new File("CoursesTemp.txt");
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter pr = new PrintWriter(br);

            try {
                String text;
                File rfile = new File("Courses.txt");
                Scanner ab = new Scanner(rfile);
                while(ab.hasNextLine()) {
                    text = ab.nextLine();
                    //System.out.println(text);
                    String[] values = text.split(",");

                    if(values[2].equals(indexNumber)) {

                        Integer newVacancy = new Integer(values[3]) - 1;
                        String Save = values[0] + ',' + values[1] + ',' + values[2] + ',' + Integer.toString(newVacancy) + "," + values[4] + "," + values[5] + "," + values[6] + "," + values[7];
                        //System.out.println(text);
                        //System.out.println(Save);
                        pr.println(Save);
                    }
                    else{
                        pr.println(text);
                    }
                }
                ab.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
                System.exit(0);
            }
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
        Files.deleteIfExists(Paths.get("Courses.txt"));
        Path source = Paths.get("CoursesTemp.txt");
        Files.move(source, source.resolveSibling("Courses.txt"));

    }
}
