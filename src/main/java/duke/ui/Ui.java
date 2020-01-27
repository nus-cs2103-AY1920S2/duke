package duke.ui;

import java.util.Scanner;

public class Ui {

    protected Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showTopLine() {
        System.out.println("\n    ________________________________________________________");
    }

    public void showBottomLine() {
        System.out.println("    ________________________________________________________\n");
    }

    public void showWelcome() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        showTopLine();
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        showBottomLine();
    }

    public void showExit() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("OOPS!!! I couldn't load your tasks from the disk! :-(");
    }

    public void showError(String message) {
        System.out.println("     " + message);
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
