package com.nus.edu.iss;

import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        String dirPath = args[0];
        File newDirectory = new File(dirPath);
        if (newDirectory.exists()){
            System.out.println(newDirectory + " exists.");
        }else{
            newDirectory.mkdir();
        }

        System.out.println("Welcome to my Shopping Cart");

        //List collection to store cart items of login user
        List<String> cartItems = new ArrayList<String>();

        //Use console class to read from keyboard input
        Console con = System.console();
        String input = "";
        String loginUser = ""; //used to keep track of current login users. also fileName to store uesr cart items

        while(!input.equals("quit")){
            input = con.readLine("What do you want to do? ");
            if(input.startsWith("login")){
                Scanner scan = new Scanner(input.substring(6));
                while(scan.hasNext()){
                    loginUser = scan.next();
                }

                //check if file <loginuser> exists
                //if not exist, create new file,
                //else (maybe) override

                File loginFile = new File (dirPath + File.separator + loginUser);

                if(loginFile.exists()){
                    System.out.println(loginUser + " exists.");
                }else{
                    loginFile.createNewFile();
                }
            }

            if(input.equals("users")){
                File directoryPath = new File(dirPath);
                String[] dirListing = directoryPath.list();
                System.out.println("List of files and directories in the specific folder " + dirPath);
                for (String dirList : dirListing){
                    System.out.println(dirList);
                }
            }
            //Add
            if (input.startsWith("add")){
                input = input.replace(',', ' '); //replace comma with space

                //1.  FileWriter & PrintWriter to write to a <loginuser> file
                FileWriter fw = new FileWriter(dirPath + File.separator + loginUser, false);
                PrintWriter pw = new PrintWriter(fw);


                String currentScanString = "";
                Scanner inputScanner = new Scanner(input.substring(4));
                while(inputScanner.hasNext()){
                    currentScanString = inputScanner.next();
                    cartItems.add(currentScanString);

                    //write to file using PrintWriter
                    pw.write("\n" + currentScanString);
                }

                //FLUSH AND CLOSE THE FILEwRITER AND PRINTWRITER OBJECTS
                pw.flush();
                pw.close();
                pw.close();


            }



        }

        

    }
}
