package Controllers;
import java.io.IOException;
import java.io.*;
import java.time.LocalTime;
import java.util.*;
import Models.Manager;
import Views.Driver;
import Models.Student;
public class registerCourse {
    public static void main(String index) throws IOException {
        Set<String> currentIndexes = new HashSet<String>();
        Set<String> currentCourses = new HashSet<String>();
        try {
            String text;
            File file = new File("RegisteredCourses.txt");
            Scanner ab = new Scanner(file);

            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");
                if(index.equals(values[1])){
                    currentIndexes.add(values[0]);
                }
            }
            ab.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        try {
            String text;
            File file = new File("Courses.txt");
            Scanner ab = new Scanner(file);

            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");
                if(currentIndexes.contains(values[2])){
                    currentCourses.add(values[1]);
                }
            }
            ab.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }


        Set<String> hashSet = new HashSet<String>();
        Set<String> IndexhashSet = new HashSet<String>();
        System.out.println("Displaying all courses:");
        try {
            String text;
            File file = new File("Courses.txt");
            Scanner ab = new Scanner(file);

            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");
                hashSet.add(values[1]);
            }
            ab.close();
            hashSet.removeAll(currentCourses);
            System.out.println(hashSet);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        Scanner sc = new Scanner(System.in);
        String courseCode;
        System.out.print("Enter Course Code you want to add: ");
        courseCode = sc.nextLine();

        while(!hashSet.contains(courseCode)){
            System.out.print("Enter Course Code you want to add: ");
            courseCode = sc.nextLine();
        }

        String indexNumber;
        try {
            String text;
            File file = new File("Courses.txt");
            Scanner ab = new Scanner(file);

            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");
                if(values[1].equals(courseCode)){
                    IndexhashSet.add(values[2]);
                }
            }
            ab.close();
            //System.out.println(hashSet);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Displaying all index numbers:");
        System.out.println(IndexhashSet);
        System.out.println("Choose an index number:");
        indexNumber = sc.nextLine();

        while(!IndexhashSet.contains(indexNumber)){
            System.out.println("Choose an index number:");
            indexNumber = sc.nextLine();
        }
        // indexNumber is what he is choosing, index is who he is. Find possible clashes.
        LocalTime startTime = null;
        LocalTime endTime = null;
        String day = null;
        try {
            String text;
            File file = new File("Courses.txt");
            Scanner ab = new Scanner(file);

            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");
                if(values[2].equals(indexNumber)){
                    day = values[4];
                    startTime = LocalTime.parse(values[6]);
                    endTime = LocalTime.parse(values[7]);
                }
            }
            ab.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        boolean clashes = false;
        try {
            String text;
            File file = new File("Courses.txt");
            Scanner ab = new Scanner(file);

            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");
                if(currentIndexes.contains(values[2]) && values[4].equals(day)){
                    if( (LocalTime.parse(values[6]).isAfter(startTime) && LocalTime.parse(values[6]).isBefore(endTime)) ||  (LocalTime.parse(values[7]).isAfter(startTime) && LocalTime.parse(values[7]).isBefore(endTime))){
                        clashes = true;
                    }
                }
            }
            ab.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        if(clashes){
            System.out.println("Course timing clashes with another registered course!");
        }
        else{
            // ADD WAITLIST CODE HERE
            Integer vacancy = 0;
            try {
                String text;
                File file = new File("Courses.txt");
                Scanner ab = new Scanner(file);

                while(ab.hasNextLine()) {
                    text = ab.nextLine();
                    //System.out.println(text);
                    String[] values = text.split(",");
                    if(values[2].equals(indexNumber)){
                        vacancy = Integer.parseInt(values[3]);
                    }
                }
                ab.close();
                //System.out.println(hashSet);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
                System.exit(0);
            }

            if(vacancy == 0){
                try {
                    String Save = indexNumber + ',' + index;
                    File file = new File("Waitlist.txt");
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
                System.out.println("Student added to waitlist!");
            }
            else{
                String Save = indexNumber + ',' + index;
                try {
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

                Controllers.decreaseVacancy.main(indexNumber);
            }
        }

    }
}
