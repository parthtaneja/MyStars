package Controllers;

import Views.Driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;
import java.io.IOException;
import java.io.*;
import java.time.LocalTime;
import java.util.*;
import Models.Manager;
import java.io.Console;
import java.util.InputMismatchException;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.util.Date;
public class dateCheck {
    public static boolean main(String args[]){
        LocalDate date = LocalDate.now();
        String start = null;
        String end = null;
        try {
            String text;
            File file = new File("Date.txt");
            Scanner ab = new Scanner(file);
            while(ab.hasNextLine()) {
                text = ab.nextLine();
                //System.out.println(text);
                String[] values = text.split(",");
                start = values[0];
                end = values[1];
            }
            ab.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        if(date.isAfter(startDate) && date.isBefore(endDate)){
            return true;
        }
        else{
            return false;
        }
    }
}
