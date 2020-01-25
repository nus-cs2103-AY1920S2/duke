package duke;

import java.util.Scanner;
import java.util.ArrayList;

public class UserInterface {
    private String logo =
            " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
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

    public Boolean isExit() {
        return this.isExit;
    }

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

    public void showSep() {
        out1(this.separator);
    }

    public void showErr(String errMsg) {
        out2(errMsg);
    }

    private void printList(ArrayList<String> list) {
        for (String items : list) {
            out2(items);
        }
    }

    public void showList(ArrayList<String> tasks) {
        if (tasks.size() > 0) {
            out2("Here are all your tasks:");
            printList(tasks);
        } else {
            out2("List is empty!");
        }
    }

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

    public void showDone(String task) {
        out2("Nice! I've marked this task as done:");
        out(task, indent3);
    }

    public void out2(String in) {
        System.out.println(" ".repeat(indent2) + in);
    }

    public void out3(String in) {
        System.out.println(" ".repeat(indent3) + in);
    }

    public void out1(String in) {
        System.out.println(" ".repeat(indent1) + in);
    }

    public void out(String in, int indent) {
        System.out.println(" ".repeat(indent) + in);
    }
}
