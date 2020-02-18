package duke.ui;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public static void printMessage(String msg) {
        //System.out.println("\t____________________________________________________________");
        System.out.println(msg);
        //System.out.println("\t____________________________________________________________");
        //System.out.println();
    }

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n");
        Ui.printMessage("Greetings! I'm Duke!\n\tPlease enter location of save file (default: data/duke.txt): ");
    }

    public static void displayAddTaskSuccessMsg(Task task, int numOfTasks) {
        Ui.printMessage("Got it! I've added the task:\n\t" + task + "\nNow you have " + numOfTasks
                        + " tasks in the list.");
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }
}