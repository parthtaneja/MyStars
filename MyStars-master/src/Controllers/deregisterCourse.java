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
public class deregisterCourse {
    /**
     * Main function to Deregister Courses for Students
     * @param index
     * @throws IOException
     */
    public static void main(String index) throws IOException {
        Set<String> hashSet = new HashSet<String>();
        System.out.println("Displaying all index numbers registered:");

        try {
            String text;
            File file = new File("RegisteredCourses.txt");
            Scanner ab = new Scanner(file);
            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");

                if(values[1].equals(index)) {
                    hashSet.add(values[0]);
                }
            }

            ab.close();
            System.out.println(hashSet);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("Enter index number to drop: ");
        String indexNumber;
        Scanner sc = new Scanner(System.in);
        indexNumber = sc.nextLine();

        File file = new File("RTemp.txt");
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        PrintWriter pr = new PrintWriter(br);

        File rfile = new File("RegisteredCourses.txt");
        Scanner ab = new Scanner(rfile);
        String text;
        while(ab.hasNextLine()) {
            text = ab.nextLine();
            //System.out.println(text);
            String[] values = text.split(",");

            if(values[1].equals(index) && values[0].equals(indexNumber)) {
                continue;
            }
            else{
                pr.println(text);
            }
        }
        ab.close();
        pr.close();
        br.close();
        fr.close();

        Files.deleteIfExists(Paths.get("RegisteredCourses.txt"));
        Path source = Paths.get("RTemp.txt");
        Files.move(source, source.resolveSibling("RegisteredCourses.txt"));

    }
}
