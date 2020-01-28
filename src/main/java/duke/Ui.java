package duke;

import duke.task.deadlineEventHash;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * The type Ui.
 */
// Deals with interactions with the user
// Shows the lines, errors etc
public class Ui {

    //private duke.task.TaskList taskList = new duke.task.TaskList();
    private deadlineEventHash deadlineEventHash = new deadlineEventHash();
    /**
     * The Lines.
     */
    String lines = "        ____________________________________________________________";
    /**
     * The Space.
     */
    String space = "        ";

    /**
     * The Command.
     */
    String command;

    /**
     * Instantiates a new Ui.
     *
     * @param command the command
     */
    public Ui(String command) {
        this.command = command;
    }

    /**
     * Instantiates a new Ui.
     */
    public Ui() {
    }

    /**
     * Show welcome.
     */
    void showWelcome() {
        String welcome_message = "____________________________________________________________\n" +
                "Hello! I'm Duke\n" + "What can I do for you today?\n" +
                "____________________________________________________________";

        // Lines are for in between the two words
        System.out.println(welcome_message);

    }

    /**
     * Print bye.
     */
    public void printBye() {
        System.out.println(lines);
        System.out.println("        Bye. Hope to see you again soon");
        System.out.println(lines);

    }


    /**
     * Print list.
     *
     * @param taskList the task list
     */
    public void printList(TaskList taskList) {
        System.out.println(lines);
        taskList.print_elements();
        System.out.println(lines);
    }

    /**
     * Invalid number exception.
     *
     * @throws DukeException the duke exception
     */
// When the user enters a number which is larger than the taskList
    public void invalidNumberException() throws DukeException {
        throw new DukeException("You have entered an invalid number!");
    }

    /**
     * Print done.
     *
     * @param finishedTask the finished task
     */
// Tells the user that the task is done.
    public void printDone(Task finishedTask) {
        System.out.println(lines);
        System.out.println(space + "Nice! I've marked this task as done");
        System.out.println(space + " [" + finishedTask.getStatusIcon() + "]"
                + " " + finishedTask.getDescription());
        System.out.println(lines);
    }

    /**
     * Print tasks.
     *
     * @param task the task
     * @param list the list
     */
    public void printTasks(Task task, ArrayList<Task> list) {
        System.out.println(lines);
        System.out.println(space + " Got it. I've added this task: ");
        System.out.println(space + task);
        System.out.println(space + " Now you have " + (list.size()) + " tasks in the list.");
        System.out.println(lines);
    }

    /**
     * Print delete.
     *
     * @param deletedTask the deleted task
     * @param taskList     the task list
     */
    public void printDelete(Task deletedTask, TaskList taskList) {
        System.out.println(lines);
        System.out.println(space + "Noted. I've removed this task:");
        System.out.println(space + deletedTask);
        System.out.println(space + "Now you have " + taskList.sizeOfList() + " tasks in the list.");
        System.out.println(lines);
    }

    /**
     * Print dont understand input.
     *
     * @throws DukeException the duke exception
     */
    void printDontUnderstandInput () throws DukeException{
        System.out.println(lines);
        throw new DukeException( "I'm sorry but I do not know what taht means :-(");
    }

    /**
     * Duke exceptions.
     *
     * @param s the s
     * @throws DukeException the duke exception
     */
// Handles any other forms of exceptions. Eg in deadline etc.
    void dukeExceptions(String s) throws DukeException{
        throw new DukeException(s);
    }

    /**
     * Read command string.
     *
     * @param command the command
     * @return the string
     * @throws DukeException the duke exception
     */
// Reads the commands
    String readCommand(String command) throws DukeException {
        if(command.contains("todo") || command.contains("deadline") ||
                command.contains("event")) {
            return "duke.command.AddCommand";
        } else if (command.contains("bye")) {
            return "duke.command.ByeCommand";
        } else if(command.contains("delete")) {
            return "duke.command.DeleteCommand";
        } else if(command.contains("list")){
            return "duke.command.ListCommand";
        } else if (command.contains("done")) {
            return "duke.command.DoneCommand";
        } else if (command.contains("find")) {
            return "duke.command.FindCommand";
        }

        else {
            throw new DukeException("You have entered a wrong command");
        }
    }

}
