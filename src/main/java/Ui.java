import java.util.Scanner;

/**
 * Handles the interactions with the user as they input to Duke.
 */
public class Ui {
    Scanner sc;

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
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void showLoadingError() {
        System.out.println("OOPS! An error occurred while loading from given file.");
    }

    public void endDuke() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readInput() {
        return sc.nextLine();
    }

    public boolean hasInputs() {
        return sc.hasNext();
    }

    public void printList(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.arr.size(); i++) {
            System.out.println(i + 1 + ". " + list.arr.get(i));
        }
    }
}
