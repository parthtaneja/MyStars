package Controllers;
import java.io.IOException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.*;
import Models.Manager;
import Models.Student;
import Views.Driver;

import java.io.Console;
public class waitlistCheck {
    public static boolean main(String indexNumber) throws IOException {
        String studentID = null;
        try {
            String text;
            File file = new File("Waitlist.txt");
            Scanner ab = new Scanner(file);
            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");
                if(values[0].equals(indexNumber)) {
                      studentID = values[1];
                }
            }
            ab.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        if (studentID == null){
            return false;
        }
        else{
            try {
                String Save = indexNumber + ',' + studentID;
                File file = new File("RegisteredCourses.txt");
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


            try {
                File file = new File("WaitlistTemp.txt");
                FileWriter fr = new FileWriter(file, true);
                BufferedWriter br = new BufferedWriter(fr);
                PrintWriter pr = new PrintWriter(br);

                try {
                    String text;
                    File rfile = new File("Waitlist.txt");
                    Scanner ab = new Scanner(rfile);
                    while(ab.hasNextLine()) {
                        text = ab.nextLine();
                        //System.out.println(text);
                        String[] values = text.split(",");
                        if(values[0].equals(indexNumber) && values[1].equals(studentID)){
                            continue;
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

            Files.deleteIfExists(Paths.get("Waitlist.txt"));
            Path source = Paths.get("WaitlistTemp.txt");
            Files.move(source, source.resolveSibling("Waitlist.txt"));
            return true;
        }
            // Write to registeredcourses with studentID and indexNumber
    }
}
