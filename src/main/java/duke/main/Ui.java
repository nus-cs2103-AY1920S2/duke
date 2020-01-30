package duke.main;

import java.util.Scanner;

public class Ui {

    static Scanner scanner = new Scanner(System.in);

    //Custom welcome Method to print a welcome message
    static void welcome() {
        print("Hello! I'm Duke\nWhat can I do for you?");
    }

    //Custom print Method to print simple inputs
    public static void print(String output) {
        System.out.println("____________________________________________________________");
        System.out.println(output);
        System.out.println("____________________________________________________________\n");
    }

    public static String getInput() {
        return scanner.nextLine();
    }

    public static void showLoadingError() {
        print("An error occurred while loading existing tasks.");
    }
}
