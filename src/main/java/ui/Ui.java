package ui;

import exception.CommandNotFoundException;
import exception.EmptyTaskListException;
import exception.InvalidIndexException;
import task.Task;
import task.TaskList;
import java.util.Scanner;

public class Ui {

    private Scanner scan;

    public Ui() {
        this.scan = new Scanner(System.in);
    }

    public boolean hasNextInput() {
        return scan.hasNext();
    }

    public String getNextInput() {
        return scan.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Ooops! Cannot find file...");
    }

    /**
     * Prints out DUKE interface.
     */
    public void print() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    /**
     * Prints exit message.
     */
    public void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
        return;
    }

    /**
     * Prints list of all tasks.
     *
     * @param tasks array list of tasks.
     */
    public void listAllTasks(TaskList tasks) throws EmptyTaskListException {
        if (tasks.getTaskListSize() == 0) {
            throw new EmptyTaskListException("There is no task in your list. Please try again...");
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.getTaskListSize(); i++) {
            System.out.println(i + ".  " + tasks.getTask(i - 1));
        }
    }

    /**
     * Prints unknown command.
     *
     * @throws CommandNotFoundException if command not recognised.
     */
    public void printUnknownCommand() throws CommandNotFoundException {
        throw new CommandNotFoundException("We don't recognise this command!!!!");
    }

    /**
     * Acknowledges once done task is done.
     *
     * @param tasks array list of tasks.
     * @param index index of which task is done.
     * @throws InvalidIndexException index out of bound.
     */
    public void acknowledgeDone(TaskList tasks, int index) throws InvalidIndexException {
        if (tasks.getTaskListSize() <= index || index < 0) {
            throw new InvalidIndexException("This index does not match any task in our list!! Try again...");
        }
        tasks.getTask(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.getTask(index));
    }

    /**
     * Acknowledges once todo task is added.
     *
     * @param tasks array list of tasks.
     * @param todo index of which task is done.
     */
    public void acknowledgeTodo(TaskList tasks, Task todo) {
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + todo);
        System.out.println("Now you have " + tasks.getTaskListSize() + " task(s) in the list.");
    }

    /**
     * Acknowledges once deadline task is added.
     *
     * @param tasks array list of tasks.
     * @param deadline time of deadline.
     */
    public void acknowledgeDeadline(TaskList tasks, Task deadline) {
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + deadline);
        System.out.println("Now you have " + tasks.getTaskListSize() + " task(s) in the list.");
    }

    /**
     * Acknowledges once event task is added.
     *
     * @param tasks array list of tasks.
     * @param event time of event.
     */
    public void acknowledgeEvent(TaskList tasks, Task event) {
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + event);
        System.out.println("Now you have " + tasks.getTaskListSize() + " task(s) in the list.");
    }

    /**
     * Acknowledges once task is deleted.
     *
     * @param tasks array list of tasks.
     * @param taskIndex index of task to be deleted.
     * @throws InvalidIndexException when index is out of bound.
     */
    public void acknowledgeDelete(TaskList tasks, int taskIndex) throws InvalidIndexException {
        if (tasks.getTaskListSize() <= taskIndex || taskIndex < 0) {
            throw new InvalidIndexException("This index does not match any task in our list!! Try again...");
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.getTask(taskIndex));
        Task targetedTask = tasks.getTask(taskIndex);
        tasks.removeTask(targetedTask);
        System.out.println("Now you have " + tasks.getTaskListSize() + " tasks in the list");
    }

    /**
     * Acknowledges when the task is searched.
     *
     * @param tasks array list of tasks.
     * @param taskSearchKey String task to search for.
     */
    public void acknowledgeFound(TaskList tasks, String taskSearchKey) {
        System.out.println("Here are the matching tasks in your list:");
        int index = 1;

        for (Task task: tasks.getCurrentTasks()) {
            String taskAction = task.toString().split(" ", 2)[1].split("\\(")[0];
            if (taskAction.contains(taskSearchKey)) {
                System.out.printf("%d. %s\n", index, task.toString());
                index++;
            }
        }
    }
}
