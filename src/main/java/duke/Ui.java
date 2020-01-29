package duke;


public class Ui {

    public void opening() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        TaskList.printList();
        printLine();

        System.out.println("What can I do for you?");
        printLine();

    }

    public void ending() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void printErrorUnderstanding() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void printErrorNoTime() {
        System.out.println("☹ OOPS!!! I'm sorry, but you need to specify the time");
    }

    public void printErrorNotFound() {
        System.out.println("☹ OOPS!!! I'm sorry, I can't find that task");
    }

    public void printErrorWrongDateFormat() {
        System.out.println("☹ OOPS!!! I'm sorry, wrong date format");
    }

    public void printErrorNoTaskName() {
        System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
    }
}
