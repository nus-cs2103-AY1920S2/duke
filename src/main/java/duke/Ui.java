package duke;

import duke.task.Task;

import java.util.Scanner;
import java.time.LocalDate;

/**
 * Interacts with the user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs a new Ui.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns the user input read.
     * @return The user input read.
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Prints the message with indentation.
     * @param message The message to be printed.
     */
    public void print(String message) {
        System.out.println("    " + message);
    }

    /**
     * Prints a horizontal line.
     */
    public void showLine() {
        print("____________________________________________________________");
    }

    /**
     * Prints the message with indentation and horizontal line above and below.
     * @param message The message to be printed.
     */
    public void showToUser(String... message) {
        showLine();
        for (String m : message) {
            print(m);
        }
        showLine();
        print("");
    }

    /**
     * Prints the welcome message when the duke application starts up.
     */
    public void showWelcome() {
        showToUser(" ____        _        ", 
                "|  _ \\ _   _| | _____ ", 
                "| | | | | | | |/ / _ \\", 
                "| |_| | |_| |   <  __/", 
                "|____/ \\__,_|_|\\_\\___|\n", 
                "Hello! I'm Duke", 
                "What can I do for you?");
    }

    /**
     * Prints the exit message when the duke application is terminated.
     */
    public void showExit() {
        showToUser("Bye. Hope to see you again soon!");
        sc.close();
    }

    /**
     * Prints the error message when an exception is thrown.
     * @param message The error message.
     */
    public void showError(String message) {
        showToUser("OOPS!!!" + message);
    }

    /**
     * Prints the tasks in the specified TaskList.
     * @param tasks The TaskList with the tasks to be printed.
     * @throws DukeException If the index is out of range (index < 1 || index > size()).
     */
    public void showTaskList(TaskList tasks) throws DukeException {
        if (tasks.isEmpty()) {
            showToUser("There are no tasks in the list.");
        } else {
            showLine();
            print("Here are the tasks in your list:");
            for (int i = 1; i <= tasks.size(); i++) {
                print(i + "." + tasks.get(i));
            }
            showLine();
            print("");
        }
    }

    /**
     * Prints a message to acknowledge the addition of the task to the TaskList.
     * @param task The task added to the TaskList.
     * @param tasks The TaskList where the task is added.
     */
    public void showAddTask(Task task, TaskList tasks) {
        showToUser("Got it. I've added this task:", "  " + task, 
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints a message to acknowledge that the task has been marked as done.
     * @param task The task marked as done.
     */
    public void showDoneTask(Task task) {
        showToUser("Nice! I've marked this task as done: ", "  " + task);
    }

    /**
     * Prints a message to acknowledge that the task has been delete from the TaskList.
     * @param task The task to be deleted from the TaskList.
     * @param tasks The TaskList where the task is deleted.
     */
    public void showDeleteTask(Task task, TaskList tasks) {
        showToUser("Noted. I've removed this task: ", "  " + task, 
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints the tasks on the specified date.
     * @param filteredTasks The TaskList that contains tasks on the specified date.
     * @param date The date to be filtered.
     */
    public void showGetTasks(TaskList filteredTasks, LocalDate date) {
        if (filteredTasks.isEmpty()) {
            showToUser("There are no tasks on " + date + ".");
        } else {
            showLine();
            print("Here are the tasks on " + date + ":");
            for (Task task : filteredTasks.getTasks()) {
                print("  " + task);
            }
            showLine();
            print("");
        }
    }
}
