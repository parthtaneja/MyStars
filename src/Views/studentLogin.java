package Views;
import java.io.IOException;
import java.io.*;
import java.time.LocalTime;
import java.util.*;
import Models.Manager;
import java.io.Console;
import java.util.InputMismatchException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class studentLogin {
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
    public static void main(Manager manager) throws NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        LocalTime time = LocalTime.now();
        //boolean studentTimeCheck =  manager.checkTime(time);
        boolean studentTimeCheck = true;
        if(studentTimeCheck)
        {
            String id;
            String password;
            System.out.print("Enter Id: ");
            id = sc.nextLine();
            System.out.print("Enter password: ");
            password = sc.nextLine();
            String hashedPwd = toHexString(getSHA(password));

            //int login = manager.validateStudent(id, password);


            try {
                String text;
                File file = new File("Students.txt");
                Scanner ab = new Scanner(file);
                while(ab.hasNextLine()) {
                    text = ab.nextLine();
                    //System.out.println(text);
                    String[] values = text.split(",");

                    if(values[0].equals(id)) {
                        //System.out.println("Found the ID");
                        if(values[3].equals(hashedPwd)) {
                            System.out.println("Login successful!");
                            ab.close();
                            manager.studentMenu(values[0]);
                        }
                        else {
                            System.out.println("Incorrect password!");
                            ab.close();
                            Driver.main(null);
                        }
                    }
                }
                System.out.println("Admin with ID: " + id + " does not exist!");
                ab.close();
                Driver.main(null);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
        else
        {
            System.out.println("Try Login in  student times");
            Driver.main(null);
        }

    }
}
