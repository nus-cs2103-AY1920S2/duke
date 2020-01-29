package duke;


public class Ui {

    /***
     * Print the opening menu
     */
    protected void opening() {
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

    /***
     * Print the closing statements
     */

    protected void ending() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /***
     * Print the straight lines
     */
    protected void printLine() {
        System.out.println("____________________________________________________________");
    }

    /***
     * Print error if command not recognised
     */
    protected void printErrorUnderstanding() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /***
     * Print error if time not specified
     */
    protected void printErrorNoTime() {
        System.out.println("☹ OOPS!!! I'm sorry, but you need to specify the time");
    }

    /***
     * Print error if task not found
     */
    protected void printErrorNotFound() {
        System.out.println("☹ OOPS!!! I'm sorry, I can't find that task");
    }

    /***
     * Print error if date entered in wrong format
     */
    protected void printErrorWrongDateFormat() {
        System.out.println("☹ OOPS!!! I'm sorry, wrong date format");
    }

    /***
     * Print error if task name not specified
     */
    protected void printErrorNoTaskName() {
        System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
    }
}
