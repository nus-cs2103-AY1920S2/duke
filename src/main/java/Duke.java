package main.java;

import java.util.Scanner;

public class Duke {

    //Function prints a simple welcome message
    public static void welcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");



    }

    public static void goodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    //function echo user input
    public static void echo() {

        Scanner in  = new Scanner(System.in);
        String command;
        myList items = new myList();

        while (true) {

            command = in.nextLine();

            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                items.printList();
            } else {
                items.addItem(command);
            }
            //System.out.println(command);
        }

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        welcomeMessage();
        echo();
        goodbyeMessage();
    }
}
