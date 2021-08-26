/**
 *     _    ____   ____ ___ ___   _                _                _        _
 *    / \  / ___| / ___|_ _|_ _| | |__   ___  _ __(_)_______  _ __ | |_ __ _| |
 *   / _ \ \___ \| |    | | | |  | '_ \ / _ \| '__| |_  / _ \| '_ \| __/ _` | |
 *  / ___ \ ___) | |___ | | | |  | | | | (_) | |  | |/ / (_) | | | | || (_| | |
 * /_/   \_\____/ \____|___|___| |_| |_|\___/|_|  |_/___\___/|_| |_|\__\__,_|_|
 *
 * This program takes 2 multiline inputs, and outputs the contents horizontally.
 * This allows you to copy paste ascii art found on the internet and even modify it
 * -you can stop the editing with ctrl+d or by pressing ENTER on an empty row
 * the program will then paste the 2 arts next to each other.
 *
 * developer: https://github.com/Hyakume1
 *
 * roadmap:
 * fast way to paste second one frm bottom, top, middle (do a menu for easy and fast editing
 * add a way to move the second pasted ascii art vertically
 * save to file
 * save to clipboard
 */

package com.company;
import java.util.ArrayList;
import java.util.Scanner;

public class Art {

    String pastedArt;
    int longestRow;
    int rowAmount;
    static String artToCopy = "uh";
    ArrayList<String> row = new ArrayList<>();

    public static void main(String[] args) {
        Art ascii1 = new Art();
        Art ascii2 = new Art();
        Scanner input = new Scanner(System.in);

        //pasting the art
        ascii1.pasteArt(input);
        System.out.print("\n\n");
        ascii2.pasteArt(input);

        //putting the horizontally and editing padding
        heightEqualizer(ascii1,ascii2);
        lengthEqualizer(ascii1);

        boolean editing = true;
        String keyStroke;
        int padding = 5; //it's easier to just convert it, this way i can also use other keys
        while(editing){
            clearScreen();
            showArts(ascii1, ascii2, padding);
            System.out.println("\n\n\n");
            System.out.println("enter the space you want between the two\n" +
                    "\"p\" to terminate the program");
            keyStroke = input.next();

            try{
                padding = Integer.parseInt(keyStroke);
            }catch(Exception e){
                if (keyStroke.contains("p")) editing = false;
            }
        }
    }//end of main

    //methods
    public Art() {
        pastedArt = "";
        longestRow = 0;
        rowAmount = 0;
    }

    //method to paste the art directly in the terminal and modify it, as well as to discern the longest row, along
    //with the amount of rows each art has
    public void pasteArt(Scanner input){
        System.out.println("Paste the ascii art");

        while(input.hasNextLine()){
            pastedArt = input.nextLine();
            row.add(pastedArt);

            if (pastedArt.length() > longestRow) longestRow = pastedArt.length();
            if (pastedArt.isEmpty()) break;
        }

        //row.remove(row.size()-1); //removing last entry due to it being a blank
        rowAmount = row.size();
        System.out.println("copied!");
    }

    //since 2 pieces of art might be different in size, am making them of equal height by filling the other with blank
    //this makes it much easier to put another right next to it later on
    static public void heightEqualizer(Art ascii1, Art ascii2){
        //if one is bigger than the other, fill the other with blanks, as big as the longest row
        if (ascii1.row.size() > ascii2.row.size()){
            ascii2.rowAmount = ascii1.row.size();
            while (ascii1.row.size() > ascii2.row.size()){
                ascii2.row.add(" ".repeat(ascii2.longestRow));
            }
        }

        else if (ascii1.row.size() < ascii2.row.size()){
            ascii1.rowAmount = ascii2.row.size();
            while (ascii1.row.size() < ascii2.row.size()){
                ascii1.row.add(" ".repeat(ascii1.longestRow));
            }
        }
    }

    //similar to height equalizer, but this takes the very first art piece and fill each row with blanks, making every
    //line the same length.
    static public void lengthEqualizer(Art ascii1){
        int difference = 0;
        for (int i  = 0; i < ascii1.rowAmount; i++){
            difference = ascii1.longestRow - ascii1.row.get(i).length();
            ascii1.row.set(i, ascii1.row.get(i).concat(" ".repeat(difference)));
        }
    }

    //this method just shows the final result
    static public void showArts(Art ascii1, Art ascii2, int horizontalSeparation){
        for(int i = 0; i < ascii1.rowAmount; i++){
            System.out.println(ascii1.row.get(i) + " ".repeat(horizontalSeparation) + ascii2.row.get(i));
        }
    }

    //clears the screen
    static public void clearScreen() {
        System.out.print("\n".repeat(100));
    }
}
