package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private String logo =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    private final String separator = "____________________________________________________________";
    private final int indent1 = 4;
    private final int indent2 = 5;
    private final int indent3 = 7;
    private Boolean isExit = false;

    private Scanner sc;

    public UserInterface() {
        this.sc = new Scanner(System.in);
        System.out.println("Hello from\n" + logo);
        System.out.println("How may duke serve the master today?\n");
    }

    /** @return Boolean indicates whether Duke should stop it's while loop */
    public Boolean isExit() {
        return this.isExit;
    }

    /**
     * Used to get user input, also sets isExit to true if user inputs exit command, "bye"
     *
     * @return String user input that has been trimmed
     */
    public String getInput() {
        if (!sc.hasNextLine()) {
            System.out.println();
            System.out.println("bye");
            this.close();
            return "bye";
        }
        String input = sc.nextLine().trim();
        System.out.println();
        System.out.println(input);
        if (input.toLowerCase().equals("bye")) {
            this.close();
            return "bye";
        }
        return input;
    }

    private void close() {
        this.isExit = true;
        this.sc.close();
    }

    /** function to print the separator for duke */
    public void showSep() {
        out1(this.separator);
    }

    /**
     * The error messages all stem from user input not matching the required format
     *
     * @param errMsg All DukeExceptions ar passed here to be printed
     */
    public void showErr(String errMsg) {
        out2(errMsg);
    }

    /** @param list list of tasks in String format */
    private void printList(ArrayList<String> list) {
        for (String items : list) {
            out2(items);
        }
    }

    /**
     * adds header for the list command and then prints list
     *
     * @param tasks list of tasks in String format
     */
    public void showList(ArrayList<String> tasks) {
        if (tasks.size() > 0) {
            out2("Here are all your tasks:");
            printList(tasks);
        } else {
            out2("List is empty!");
        }
    }

    /**
     * adds header for the search command and then prints matched tasks
     *
     * @param tasks list of tasks in String format
     */
    public void showSearch(ArrayList<String> tasks) {
        if (tasks.size() > 0) {
            out2("Here are the matches:");
            printList(tasks);
        } else {
            out2("No matches found!");
        }
    }

    public void showBye() {
        out2("Bye. Hope to see you again soon!");
    }

    /**
     * adds header to indicate that done command was performed followed by modified task
     *
     * @param task task in String format
     */
    public void showDone(String task) {
        out2("Nice! I've marked this task as done:");
        out(task, indent3);
    }

    /**
     * print with level 2 indentation
     *
     * @param in String to print
     */
    public void out2(String in) {
        System.out.println(" ".repeat(indent2) + in);
    }

    /**
     * print with level 3 indentation
     *
     * @param in String to print
     */
    public void out3(String in) {
        System.out.println(" ".repeat(indent3) + in);
    }

    /**
     * print with level 1 indentation
     *
     * @param in String to print
     */
    public void out1(String in) {
        System.out.println(" ".repeat(indent1) + in);
    }

    /**
     * @param in String to print
     * @param indent level of indentation
     */
    public void out(String in, int indent) {
        System.out.println(" ".repeat(indent) + in);
    }
}
