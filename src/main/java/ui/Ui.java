package ui;

import exception.CommandNotFoundException;
import exception.EmptyTaskListException;
import exception.InvalidIndexException;
import task.Task;
import task.TaskList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ui {

    public Ui() {}


    public void showLoadingError() {
        System.out.println("Ooops! Cannot find file...");
    }

    String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/\\__,_|_|\\_\\___|\n";

    private String welcomeMessage = "Hello from\n" + logo + "What can I do for you?";

    public String getWelcomeMessage() {
        return this.welcomeMessage;
    }

    /**
     * Prints out error message.
     *
     * @param message
     * @return
     */
    public String showCommandError(String message) {
        return "Error: " + message;
    }

    /**
     * Prints exit message.
     *
     */
    public String getExitMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Prints unknown command.
     *
     * @throws CommandNotFoundException if command not recognised.
     */
    public static String printUnknownCommand() throws CommandNotFoundException {
        throw new CommandNotFoundException("We don't recognise this command!!!!\n");
    }

    /**
     * Prints acknowledgement after a task is added.
     *
     * @param tasks is the task list.
     * @param task is the added task.
     * @return a String message.
     */
    public String acknowledgeTaskAdded(TaskList tasks, Task task) {
        return String.format("Got it. I've added this task\n    %s\nNow you have %d task(s) in the list.\n",
                task, tasks.getTaskListSize());
    }

    /**
     * Prints acknowledgement message after searching for task.
     *
     * @param tasks is the task list.
     * @param taskSearchKey is the keyword to search.
     * @return a String message.
     */
    public String acknowledgeFound(TaskList tasks, String taskSearchKey) {
        String s = "Here are the matching tasks in your list:\n";
        AtomicInteger atomicIndex = new AtomicInteger(1);
        ArrayList<Task> taskList = tasks.getCurrentTasks();

        List<String> foundTaskList =
                taskList.stream()
                        .filter(task -> task.toString().split(" ", 2)[1].split("\\(")[0]
                                .contains(taskSearchKey))
                        .map(task -> String.format("%d.  %s\n", atomicIndex.getAndIncrement(), task))
                        .collect(Collectors.toList());

        foundTaskList.add(0, s);
        return String.join("", foundTaskList);
    }

    /**
     * Prints tasks marked as done.
     *
     * @param tasks is task list.
     * @param arrayOfDoneIndexes is array of tasks to be marked done.
     * @return String response.
     * @throws InvalidIndexException is exception thrown when the indexes are invalid.
     */
    public String acknowledgeDone(TaskList tasks, int[] arrayOfDoneIndexes) throws InvalidIndexException {
        if (arrayOfDoneIndexes.length == 0) { throw new InvalidIndexException("Index does not match any task!\n"); }

        String s = "Nice! I've marked this task(s) as done:\n";

        List<String> listOFPrintedDoneTasks =
                IntStream.of(arrayOfDoneIndexes)
                         .mapToObj(index -> String.format("%d.  %s\n", index + 1, tasks.getTask(index)))
                         .collect(Collectors.toList());

        String taskSizeMessage = this.currentTaskListSize(tasks);

        listOFPrintedDoneTasks.add(0, s);

        listOFPrintedDoneTasks.add(taskSizeMessage);

        return String.join("", listOFPrintedDoneTasks);
    }

    /**
     * Prints acknowledgement of deleted tasks.
     *
     * @param tasks is task list.
     * @param arrayOfDeleteIndexes are indexes of tasks to be deleted.
     * @return a String response.
     * @throws InvalidIndexException is thrown if indexes are invalid.
     */
    public String acknowledgeDelete(TaskList tasks, int[] arrayOfDeleteIndexes) throws InvalidIndexException {
        String s = "Noted. I've removed this task(s):\n";

        if (arrayOfDeleteIndexes.length == 0) { throw new InvalidIndexException("Index does not match any task!\n"); }

        List<String> listOFPrintedDeleteTasks =
                IntStream.of(arrayOfDeleteIndexes)
                         .mapToObj(index -> String.format("%d.  %s\n", index + 1, tasks.getTask(index)))
                         .collect(Collectors.toList());

        listOFPrintedDeleteTasks.add(0, s);

        return String.join("", listOFPrintedDeleteTasks);
    }

    /**
     * Prints out the current tasks in task list.
     *
     * @param tasks is the task list.
     * @return a String response.
     * @throws EmptyTaskListException if the current task list is empty.
     */
    public String acknowledgeList(TaskList tasks) throws EmptyTaskListException {
        if (tasks.getTaskListSize() == 0) {
            throw new EmptyTaskListException("There is no task in your list. Please try again...");
        }
        String s = "Here are the tasks in your list:\n";
        AtomicInteger atomicIndex = new AtomicInteger(1);

        for (Task task: tasks.getCurrentTasks()) {
            s += String.format("%d.  %s\n", atomicIndex.getAndIncrement(), task);
        }

        return s;
    }

    /**
     * Prints current size of the task list.
     *
     * @param tasks is the task list.
     * @return a String response.
     */
    public String currentTaskListSize(TaskList tasks) {
        return String.format("Now you have " + tasks.getTaskListSize() + " tasks in the list");
    }
}
