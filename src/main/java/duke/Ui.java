package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains interaction with user, meaning printing and reading through the scanner.
 */
public class Ui {
    Scanner scanner;

    Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        String line = "-----------------------------------";
        System.out.println(line);
    }

    public void showError(String errorMessage) {
        showLine();
        System.out.println(errorMessage);
        showLine();
    }

    public void showWelcome() {
        showLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        showLine();
    }

    public void printWithFormat(String input, String type, TaskList tasks) {
        switch (type) {
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= tasks.getLength(); i++) {
                    StringBuilder str = new StringBuilder();
                    Task task = tasks.get(i-1);
                    String output = str.append(i).append(". ").append(task.toString()).toString();
                    System.out.println(output);
                }
                break;
            case "task":
                System.out.println("Got it. I've added this task:");
                System.out.println(input);
                int arrlength = tasks.getLength();
                System.out.println("Now you have "+ arrlength + " tasks in the list.");
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "done":
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(input);
                break;
            case "delete":
                System.out.println("Noted. I've removed this task:");
                System.out.println(input);
                System.out.println("Now you have "+ tasks.getLength() + " tasks in the list.");
                break;
            case "find":
                System.out.println("Here are the matching tasks in your list:");
                for (int i = 1; i <= tasks.getLength(); i++) {
                    StringBuilder str = new StringBuilder();
                    Task task = tasks.get(i-1);
                    String output = str.append(i).append(". ").append(task.toString()).toString();
                    System.out.println(output);
                }
                break;
            default:
                System.out.println(input);
                break;
        }
    }


    public String readCommand() {
        return scanner.nextLine();
    }
}
