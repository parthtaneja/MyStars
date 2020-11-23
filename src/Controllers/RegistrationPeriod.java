
package Controllers;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class RegistrationPeriod {

    // this function will check if date on which function is called is in between the eligible dates.
    public Boolean checkPeriod() {

        Date dstart = new Date();
        Date dend = new Date();
        Date dnow = new Date();


        // this part will read the openndate file in specific formate
        try (BufferedReader in = new BufferedReader(new FileReader("opendate.txt"))) {
            String str;
            while ((str = in.readLine()) != null) {
                // splitting lines on the basis of token
                String[] tokens = str.split(",");
                String[] d1 = tokens[0].split("-");
                String[] d2 = tokens[1].split("-");

                int a = Integer.parseInt(d1[0]);
                dstart.setDate(a);
                a = Integer.parseInt(d1[1]);
                dstart.setMonth(a);
                a = Integer.parseInt(d1[2]);
                dstart.setYear(a);

                a = Integer.parseInt(d2[0]);
                dend.setDate(a);
                a = Integer.parseInt(d2[1]);
                dend.setMonth(a);
                a = Integer.parseInt(d2[2]);
                dend.setYear(a);

            }
        } catch (Exception e) {
            System.out.println("File Read Error open");
        }


        if (dnow.before(dend) && dnow.after(dstart))
            return true;

        return false;
    }

    public void setRegistrationPeriod() {
        System.out.println("Setting Start Date");
        Scanner d1 = new Scanner(System.in);
        String D22 = "", Y22 = "", M22 = "", M11 = "", D11 = "", Y11 = "";
        Integer D1;
        D1 = -1;
        while (D1 > 31 || D1 < 0) {
            System.out.println("Setting Start Day:");
            D1 = d1.nextInt();
            D11 = D1.toString();
        }
        Scanner m1 = new Scanner(System.in);
        Integer M1;
        M1 = -1;
        while (M1 > 12 || M1 < 0) {
            System.out.println("Setting Start Month:");
            M1 = m1.nextInt();
            M11 = M1.toString();
        }
        Scanner y1 = new Scanner(System.in);
        Integer Y1;
        Y1 = 0;
        while (Y1 > 2100 || Y1 < 1900) {
            System.out.println("Setting Start Year:");
            Y1 = y1.nextInt();
            Y11 = Y1.toString();
        }


        Scanner d2 = new Scanner(System.in);
        Integer D2;
        D2 = -1;
        while (D2 > 31 || D2 < 0) {
            System.out.println("Setting End Day:");
            D2 = d2.nextInt();
            D22 = D2.toString();
        }
        Scanner m2 = new Scanner(System.in);
        Integer M2;
        M2 = -1;
        while (M2 > 12 || M2 < 0) {
            System.out.println("Setting End Month:");
            M2 = m2.nextInt();
            M22 = M2.toString();
        }
        Scanner y2 = new Scanner(System.in);
        Integer Y2;
        Y2 = 0;
        while (Y2 > 2100 || Y2 < 1900) {
            System.out.println("Setting End Year:");
            Y2 = y2.nextInt();
            Y22 = Y2.toString();
        }

        Scanner t1 = new Scanner(System.in);
        Integer T1;
        T1 = -1;
        while (T1 > 24 || T1 < 0) {
            System.out.println("Setting Start Time:");
            T1 = t1.nextInt();
            String T11 = T1.toString();
        }

        Scanner t2 = new Scanner(System.in);
        Integer T2;
        T2 = -1;
        while (T2 > 24 || T2 < 0) {
            System.out.println("Setting End Time:");
            T2 = t2.nextInt();
            String T22 = T2.toString();
        }

        try {
            var myWriter = new FileWriter("opendate.txt");
            String dash = "-";
            String line = "";
            line = D11 + dash + M11 + dash + Y11 + "," + D22 + dash + M22 + dash + Y22+","+T1+","+T2;
            myWriter.write(line);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

    }

    public boolean read() {
        try (BufferedReader in = new BufferedReader(new FileReader("opendate.txt"))) {
            String str;
            System.out.println("dog");
            while ((str = in.readLine()) != null) {
                // splitting lines on the basis of token
                String[] tokens = str.split(",");
                String t1[] = tokens[0].split("-");
                String t2[] = tokens[1].split("-");
                String t33 = tokens[2];
                String t44 = tokens[3];

                String d1 = t1[0];
                String m1 = t1[1];
                String y1 = t1[2];
                String d2 = t2[0];
                String m2 = t2[1];
                String y2 = t2[2];

                int d11 = Integer.parseInt(d1);
                int m11 = Integer.parseInt(m1);
                int y11 = Integer.parseInt(y1);
                int d22 = Integer.parseInt(d2);
                int m22 = Integer.parseInt(m2);
                int y22 = Integer.parseInt(y2);
                int tstart = Integer.parseInt(t33);
                int tend = Integer.parseInt(t44);

                //System.out.println("dogggy");

                DateTime start = new DateTime(y11, m11, d11, 0, 0, 0, 0);
                DateTime end = new DateTime(y22, m22, d22, 0, 0, 0, 0);
                DateTime now = new DateTime();

                int hour = now.getHourOfDay();
                //System.out.println(hour);
                //System.out.println(tstart);
                //System.out.println(tend);
                boolean a = false;

                if (hour > tstart && hour < tend) {
                    //System.out.println("hi");
                    a = true;
                }

                Interval interval = new Interval(start, end);

                //System.out.println(interval.contains(now));

                boolean timing = interval.contains(now) && a;

                return timing;


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}