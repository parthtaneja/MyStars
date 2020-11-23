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
public class changeIndex {
    /**
     * main function
     * To Change index number for each individual
     * @param studentID ID of student
     * @return void
     */
    public static void main(String index) throws IOException {
        Scanner sc = new Scanner(System.in);
        String indexNumber;
        System.out.println("Index number to change: ");
        indexNumber = sc.nextLine();
        System.out.println("Available index numbers to switch with:");
        Set<String> hashSet = new HashSet<String>();
        String courseCode = null;

        String text;
        File file = new File("Courses.txt");
        Scanner ab = new Scanner(file);
        while(ab.hasNextLine()) {
            text = ab.nextLine();
            //System.out.println(text);
            String[] values = text.split(",");

            if(values[2].equals(indexNumber)) {
                courseCode = values[1];
            }
        }
        ab.close();




        File rfile = new File("Courses.txt");
        Scanner abc = new Scanner(rfile);
        while(abc.hasNextLine()) {
            text = abc.nextLine();
            //System.out.println(text);
            String[] values = text.split(",");

            if(values[1].equals(courseCode)) {
                hashSet.add(values[2]);
            }
        }
        abc.close();

        hashSet.remove(indexNumber);
        System.out.println(hashSet);
        System.out.println("Choose a new index number: ");
        String newIndex;
        newIndex = sc.nextLine();
        while(!hashSet.contains(newIndex)){
            System.out.println("Choose a new index number: ");
            newIndex = sc.nextLine();
        }

        try {
            File wfile = new File("RTemp.txt");
            FileWriter fr = new FileWriter(wfile, true);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter pr = new PrintWriter(br);

            File Readfile = new File("RegisteredCourses.txt");
            Scanner xy = new Scanner(Readfile);
            while(xy.hasNextLine()) {
                text = xy.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");

                if(values[0].equals(indexNumber) && values[1].equals(index)) {
                    String Save = newIndex + ',' + index;
                    pr.println(Save);
                }
                else{
                    pr.println(text);
                }
            }
            xy.close();
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

        Files.deleteIfExists(Paths.get("RegisteredCourses.txt"));
        Path source = Paths.get("RTemp.txt");
        Files.move(source, source.resolveSibling("RegisteredCourses.txt"));
    }
}
