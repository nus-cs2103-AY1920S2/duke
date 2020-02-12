package ui;

import exception.CommandNotFoundException;
import exception.EmptyTaskListException;
import exception.InvalidIndexException;
import task.Task;
import task.TaskList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

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
        AtomicInteger atomicIndex = new AtomicInteger(1);

        for (Task task: tasks.getCurrentTasks()) {
            System.out.printf("%d.  %s\n", atomicIndex.getAndIncrement(), task);
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
     * Acknowledges when the task(s) has been completed.
     *
     * @param tasks array list of current tasks
     * @param arrayOfIndexes varargs for inputted index(es) of task to mark as complete.
     * @throws InvalidIndexException if any of the index is invalid
     */
    public void acknowledgeDone(TaskList tasks, int... arrayOfIndexes) throws InvalidIndexException {
        int[] arrayOfDoneIndexes = IntStream.of(arrayOfIndexes)
                                            .filter(index -> tasks.getTaskListSize() > index && index >= 0)
                                            .toArray();

        if (arrayOfDoneIndexes.length == 0) { throw new InvalidIndexException("Index does not match any task!"); }
        System.out.println("Nice! I've marked this task(s) as done:");

        IntStream.of(arrayOfDoneIndexes)
                 .forEach(index -> tasks.getTask(index).markAsDone());

        IntStream.of(arrayOfDoneIndexes)
                 .forEach(index -> System.out.printf("%d.  %s\n", index + 1, tasks.getTask(index)));
    }


    /**
     * Acknowledges once todo task is added.
     *
     * @param tasks array list of tasks.
     * @param todo index of which task is done.
     */
    public void acknowledgeTodo(TaskList tasks, Task todo) {
        System.out.printf("Got it. I've added this task\n    %s\nNow you have %d task(s) in the list.",
                todo, tasks.getTaskListSize());
    }

    /**
     * Acknowledges once deadline task is added.
     *
     * @param tasks array list of tasks.
     * @param deadline time of deadline.
     */
    public void acknowledgeDeadline(TaskList tasks, Task deadline) {
        System.out.printf("Got it. I've added this task\n    %s\nNow you have %d task(s) in the list.",
                deadline, tasks.getTaskListSize());
    }

    /**
     * Acknowledges once event task is added.
     *
     * @param tasks array list of tasks.
     * @param event time of event.
     */
    public void acknowledgeEvent(TaskList tasks, Task event) {
        System.out.printf("Got it. I've added this task\n    %s\nNow you have %d task(s) in the list.",
                event, tasks.getTaskListSize());
    }

    /**
     * Acknowledges once task is deleted.
     *
     * @param tasks array list of tasks.
     * @param arrayOfIndexes integer array of task indexes to be deleted.
     * @throws InvalidIndexException when index is out of bound.
     */
    public void acknowledgeDelete(TaskList tasks, int... arrayOfIndexes) throws InvalidIndexException {
        System.out.println("Noted. I've removed this task(s):");
        int[] arrayOfDeleteIndexes = IntStream.of(arrayOfIndexes)
                                              .filter(index -> tasks.getTaskListSize() > index && index >= 0)
                                              .toArray();

        IntStream.of(arrayOfDeleteIndexes)
                 .forEach(index -> System.out.printf("%d.  %s\n", index + 1, tasks.getTask(index)));

        tasks.removeTasks(arrayOfDeleteIndexes);
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
        AtomicInteger atomicIndex = new AtomicInteger(1);
        ArrayList<Task> taskList = tasks.getCurrentTasks();

        taskList.stream()
                .filter(task -> task.toString().split(" ", 2)[1].split("\\(")[0]
                        .contains(taskSearchKey))
                .forEach(task -> {
                    System.out.printf("%d.  ", atomicIndex.getAndIncrement());
                    System.out.println(task);
                });
    }
}
