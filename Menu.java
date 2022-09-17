package grading;

import java.util.Locale;
import java.util.Scanner;

class GradingSystem {

    //scanner to get inputs
    static Scanner scanner = new Scanner(System.in);
    //an array to store marks
    static int[] marks = new int[7];
    //to store maxmimum grade
    static int max = 0;

    /*TASK 1*/public static void main(String[] args)
    {

        //get inputs
        for (int i = 0; i < 7; i++) {

            //print a message
            if (i + 1 == 1) {
                System.out.print("Enter the score for the 1st student ");
            } else if (i + 1 == 2) {
                System.out.print("Enter the score for the 2nd student ");
            } else if (i + 1 == 3) {
                System.out.print("Enter the score for the 3rd student ");
            } else {
                System.out.print("Enter the score for the " + (i + 1) + "th student ");
            }

            //get an input
            int mark = Integer.parseInt(scanner.nextLine());

            //input is not valid
            if (mark < 0 || mark > 100) {
                System.out.println("Error - Input out of bound. Score can only be between 0 and 100.");
                i--;//reduce i to take this record agian
            } //valid
            else {
                marks[i] = mark;
            }
        }

        System.out.println("Thank you for your input. Your entered scores are:");
        //print all
        int i;
        for (i = 0; i < 6; i++) {
            System.out.print(marks[i] + ", ");//print 6 marks with a comma
        }
        System.out.print(marks[i]);//print last mark without a comma
        int option;
        //infinite loop, until choose 6th option
        do {

            //print menu
            System.out.print("\n\nWelcome to the menu. Choose one of the options below:\n"
                    + "\t1. Register new scores for students.\n"
                    + "\t2. Calculate the mean of the entered scores.\n"
                    + "\t3. Find the two highest and two lowest scores.\n"
                    + "\t4. Find the highest score and its position.\n"
                    + "\t5. Collect hashtags from a post.\n"
                    + "\t6. To exit.\n"
                    + "Type your option: ");

            //get option
            option = Integer.parseInt(scanner.nextLine());

            System.out.println("\n");
            if (option == 1) {
                getInputs();
            } else if (option == 2) {
                printMean();
            } else if (option == 3) {
                printTwoLowestAndTwoHighestGrades();
            } else if (option == 4) {
                printHighestAndIndex();
            } else if (option == 5) {
                printHashTags();
            } else if (option == 6) {
                System.out.println("Thank you for using our grading system. Have a nice day!");
                break;//break while loop to exit

            } else {//invalid option
                System.out.println("“Error - Invalid value. Please type between 1 and 6");
            }
        }while (option!=6);
    }

    private static void getInputs() {
    }


    /*TASK 2*/
    public static void printMean() {

        //calculating sum
        int total = 0;//initialize to 0
        for (int i = 0; i < 7; i++) {
            total += marks[i]; // add every mark together
        }
        //calculating mean
        double mean = total / 7.0;

        Locale.setDefault(Locale.ENGLISH);
        System.out.printf("The mean of the numbers is %.2f", mean); //prints upto two decimal points

    }

    /*TASK 3*/
    public static void printTwoLowestAndTwoHighestGrades() {

        //create variables to store two highest grades and two lowest gades
        //intialize them
        int highest = -1;
        int second_highest = -1;
        int lowest = 101;
        int second_lowest = 101;

        //finding two highest grades and two lowest gades
        for (int i = 0; i < 7; i++) {
            if (highest < marks[i]) {
                second_highest = highest;
                highest = marks[i];
            } else if (second_highest < marks[i]) {
                second_highest = marks[i];
            }

            if (lowest > marks[i]) {
                second_lowest = lowest;
                lowest = marks[i];
            } else if (second_lowest > marks[i]) {
                second_lowest = marks[i];
            }
        }

        //print them
        System.out.println("The two lowest scores provided are " + lowest + ", and " + second_lowest);
        System.out.print("The two highest scores provided are " + highest + ", and " + second_highest);

        //set max
        max = highest;
    }

    /*Task 4*/
    public static void printHighestAndIndex() {
        //since we have already found the highest score we dont have find it again
        //lets find the index of it
        int i;
        for (i = 0; i < 7; i++) {
            if (marks[i] == max) {//ith element is the highest
                break; //stop the loop there
            }
        }
        //print
        if (i + 1 == 1) {
            System.out.println("\nThe highest score is " + max + " and belongs to the 1st student");
        } else if (i + 1 == 2) {
            System.out.println("\nThe highest score is " + max + " and belongs to the 2nd student");
        } else if (i + 3 == 5) {
            System.out.println("\nThe highest score is " + max + " and belongs to the 3rd student");
        } else {
            System.out.println("\nThe highest score is " + max + " and belongs to the " + (i + 1) + "th student");
        }
    }

    /*Task 5*/
    public static void printHashTags() {

        //to store tags
        String[] hashTags = new String[20];
        //number of tags
        int numberOfTags = 0;

        //scan a post
        System.out.print("Type your post: ");
        String post = scanner.nextLine();

        boolean hash = false; //to indicate has symbol has been found
        String hashTag = ""; //to store one hashtag
        //check character by character
        for (int i = 0; i < post.length(); i++) {

            if (hash) {//if we found a hash symbol before
                if (!(('a' <= post.charAt(i) && 'z' >= post.charAt(i))
                        || ('A' <= post.charAt(i) && 'Z' >= post.charAt(i))) || i == post.length() - 1) {//end of hash tag

                    if (i == post.length() - 1) {//if last character
                        hashTag += post.charAt(i);//add character
                    }

                    if (hashTag.length() > 0) {//store it, if it is not empty
                        hashTags[numberOfTags++] = hashTag;
                    }
                    hash = false; //indicate new hashtag is end
                    hashTag = "";//clear the variable

                } else {//otherwise
                    hashTag += post.charAt(i);//add character
                }
            }

            if (post.charAt(i) == '#') {//when a hash symbol is found, make hash true
                hash = true;
            }

        }

        if (numberOfTags == 0) {//no hash tags
            System.out.println("No hashtags were typed");

        } else { //there were hashtags
            System.out.print("Hashtags found: ");
            for (int i = 0; i < numberOfTags; i++) {
                System.out.print("#" + hashTags[i]);
            }
        }

    }}