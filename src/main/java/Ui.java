import java.util.List;

public class Ui {
    public Ui() {
    }

    private static void print(String s) {
        System.out.println(s);
    }

    private void printLine() {
        print("____________________________________________________________");
    }

    public void greet() {
        printLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        print("Hello from\n" + logo);
        print("What can I do for you?");
        printLine();
    }

    public void displayTasks(List<Task> tasks) throws DukeInvalidTaskException {
        if (tasks.size() != 0) {
            print("Here are the tasks in your list:");
            for (int i = 1; i <= tasks.size(); i++) {
                print(i + "." + tasks.get(i - 1));
            }
            printLine();
        } else {
            throw new DukeInvalidTaskException("There are no remaining tasks in the list");
        }
    }

    public static void printError(Exception e) {
        System.err.println(e);
        print("Try again later.");
    }

    public static void printInvalidDateFormatError() {
        System.err.println("Please enter the date as yyyy-mm-dd followed by the time.");
        print("Try again later.");
    }


    public void printBye() {
        print("Bye. Hope to see you again soon!");
        printLine();
    }

    public void printTerminated() {
        print("Program terminated.");
    }
}
