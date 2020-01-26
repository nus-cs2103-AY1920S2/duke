package main.java;
import java.util.Scanner;

public class Ui {


    public Ui () {}

    public void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Greetings, I am\n" + logo);
        System.out.println("How may I be of assistance today?");
    }

    public void printDone(Task task) {
        System.out.println("You have completed this task.");
        System.out.println(task);
    }

    public void printDelete(Task task, int newListSize) {
        System.out.println(task + " has been removed.");
        System.out.println("Number of items in the list: " + newListSize);
    }

    public void printAdd(Task task, int listSize) {
        System.out.println("Understood. I have added: " + task);
        System.out.println("Number of items in the list: " + listSize);
    }


    public void printBye() {
        System.out.println("It was my pleasure to help you.\n");
    }
}
