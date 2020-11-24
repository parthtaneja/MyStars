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
public class swopIndex {
    /**
     * Swap Index with another students from another student
     * @param index
     * @throws IOException
     */
    public static void main(String index) throws IOException {
        String studentID;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter friend's studentID: ");
        studentID = sc.nextLine();
        Set<String> hashSet = new HashSet<String>();
        Boolean flag = false;
        try {
            String text;
            File file = new File("Students.txt");
            Scanner ab = new Scanner(file);
            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");
                hashSet.add(values[0]);
            }
            ab.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        while(!hashSet.contains(studentID)){
            System.out.println("Enter friend's studentID: ");
            studentID = sc.nextLine();
        }
        String password;
        System.out.println("Friend's password for authorisation: ");
        password = sc.nextLine();
        Set<String> passwordsSet = new HashSet<String>();
        try {
            String text;
            File file = new File("Students.txt");
            Scanner ab = new Scanner(file);
            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");

                if(values[0].equals(studentID)) {
                    passwordsSet.add(values[3]);
                }
            }
            ab.close();

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        while(!passwordsSet.contains(password)){
            System.out.println("Friend's password for authorisation: ");
            password = sc.nextLine();
        }

        System.out.println("Displaying all your index numbers: ");
        Set<String> IndexhashSet = new HashSet<String>();
        try {
            String text;
            File file = new File("RegisteredCourses.txt");
            Scanner ab = new Scanner(file);
            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");

                if(values[1].equals(index)) {
                    IndexhashSet.add(values[0]);
                }
            }

            ab.close();
            System.out.println(IndexhashSet);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        String indexToSwap;
        System.out.println("Choose your index number to swop: ");
        indexToSwap = sc.nextLine();
        while(!IndexhashSet.contains(indexToSwap)){
            System.out.println("Choose your index number to swop: ");
            indexToSwap = sc.nextLine();
        }

        // CHECK WHICH COURSE INDEXTOSWAP IS FROM AND FIND THE OTHER USERS INDEX IN THE SAME COURSE
        String courseCode = null;
        try {
            String text;
            File file = new File("Courses.txt");
            Scanner ab = new Scanner(file);
            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");

                if(values[2].equals(indexToSwap)) {
                    courseCode = values[1];
                }
            }
            ab.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        Set<String> allIndexinCourse = new HashSet<String>();
        try {
            String text;
            File file = new File("Courses.txt");
            Scanner ab = new Scanner(file);
            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");

                if(values[1].equals(courseCode)) {
                    allIndexinCourse.add(values[2]);
                }
            }
            ab.close();
            allIndexinCourse.remove(indexToSwap);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        Set<String> possibleSwitches = new HashSet<String>();
        try {
            String text;
            File file = new File("RegisteredCourses.txt");
            Scanner ab = new Scanner(file);
            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");

                if(values[1].equals(studentID) && allIndexinCourse.contains(values[0])) {
                    possibleSwitches.add(values[0]);
                }
            }
            ab.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Possible indexes to switch with:");
        System.out.println(possibleSwitches);

        System.out.println("Enter index to swap with: ");
        String swappingIndex;
        swappingIndex = sc.nextLine();
        while(!possibleSwitches.contains(swappingIndex)){
            System.out.println("Enter index to swap with: ");
            swappingIndex = sc.nextLine();
        }

        try {
            File file = new File("RTemp.txt");
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter pr = new PrintWriter(br);

            try {
                String text;
                File rfile = new File("RegisteredCourses.txt");
                Scanner ab = new Scanner(rfile);
                while(ab.hasNextLine()) {
                    text = ab.nextLine();
                    //System.out.println(text);
                    String[] values = text.split(",");

                    if((values[1].equals(index) && values[0].equals(indexToSwap)) || (values[1].equals(studentID) && values[0].equals(swappingIndex))) {
                        continue;
                    }
                    else{
                        pr.println(text);
                    }
                }
                text = swappingIndex + ',' + index;
                pr.println(text);
                text = indexToSwap + ',' + studentID;
                pr.println(text);
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
        Files.deleteIfExists(Paths.get("RegisteredCourses.txt"));
        Path source = Paths.get("RTemp.txt");
        Files.move(source, source.resolveSibling("RegisteredCourses.txt"));
    }
}
