package duke;

import java.util.Scanner;

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
        out(this.separator, indent1);
    }

    public void showErr(String errMsg) {
        out(errMsg, indent2);
    }

    public void showList(String[] tasks) {
        out("Here are the tasks in your list:", indent2);
        for (String s : tasks)
            out(s, indent2);
    }

    public void showBye() {
        out("Bye. Hope to see you again soon!", indent2);
    }

    public void showDone(String task) {
        out("Nice! I've marked this task as done:", indent2);
        out(task, indent3);
    }

    public void out2(String in) {
        System.out.println(" ".repeat(indent2) + in);
    }

    public void out3(String in) {
        System.out.println(" ".repeat(indent3) + in);
    }

    public void out(String in, int indent) {
        System.out.println(" ".repeat(indent) + in);
    }
}
