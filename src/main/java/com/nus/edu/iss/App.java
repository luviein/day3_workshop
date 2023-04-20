package com.nus.edu.iss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
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

            //users must be logged in first
            //must perform the following first
            //eg. login <loginuser>
            if (input.equals("list")){
                //need a file class and bufferred reader class (cos everything stored as a line to read cart items from the file)
                File readFile = new File (dirPath + File.separator + loginUser);
                BufferedReader br = new BufferedReader(new FileReader(readFile));
                String readFileInput = "";
                

                //reset cartItems list collection
                cartItems = new ArrayList<String>();
                
                //while loop to read through all the item records in the file
                while((readFileInput = br.readLine()) != null) {
                    System.out.println(readFileInput);
                    cartItems.add(readFileInput);
                }
                //exit from while loop -close the buffered reader class/object
                br.close();

            }

            if(input.startsWith("delete")){
                //stringVal[0] = delete
                //stringVal[1] = index to delete from cartItems
                //can also use s
                //String valueData = input.substring(7);
                String [] stringVal = input.split(" ");

                //remove 3rd item in cartItems arraylist
                int deleteIndex = Integer.parseInt(stringVal[1]);
                if (deleteIndex <= cartItems.size()){
                    cartItems.remove(deleteIndex);

                }else{
                    System.out.println("Index out of range error");
                }
                
                //1. Open FileWriter and BufferedWriter
                FileWriter fw2 = new FileWriter(dirPath + File.separator + loginUser, false);
                BufferedWriter bw = new BufferedWriter(fw2);


                //2. Loops to write cartItems to file
                int listIndex = 0;
                while(listIndex < cartItems.size()){
                    bw.write(cartItems.get(listIndex));
                    bw.newLine();
                    
                    listIndex ++;
                }


                //3. Close BufferedWriter and FileWriter
                bw.flush();
                bw.close();
                fw2.close();
            }   


        } // end of while loop
            


    }

        

}

