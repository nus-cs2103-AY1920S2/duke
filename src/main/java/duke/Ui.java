package duke;

import java.util.Scanner;

public class Ui {
    protected Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
    }

    public String receiveInput() {
        return sc.nextLine();
    }

    public void exceptionMessage(DukeException e) {
        System.out.println(e);
    }

    public void userMessage(String msg) {
        System.out.println(msg);
    }

    public void close() {
        sc.close();
    }

    public void fareWellMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
