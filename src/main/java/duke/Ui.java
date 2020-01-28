package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    protected Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        this.showLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        this.showLine();
    }

    public void showExit() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public void showError(String msg) {
        System.out.println("     " + msg);
    }

    public void showLoadingError() {
        this.showLine();
        System.out.println("     OOPS!!! Fail to load data.");
        this.showLine();
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("    ----------------------------------------------------------");
    }

    public void showMessages(String[] messages) {
        for (String s : messages) {
            System.out.println("     " + s);
        }
    }

    public void showList(ArrayList<Task> tasks) {
        System.out.println("     Here are the tasks in your list:");
        int size = tasks.size();
        for (int i = 0; i < size; i++) {
            System.out.println("     " + (i + 1) + ". " + tasks.get(i).toString());
        }
    }
}
