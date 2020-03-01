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

    public String showCommandError(String message) {
        return "Error: " + message;
    }

    /**
     * Prints exit message.
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

    public String acknowledgeTaskAdded(TaskList tasks, Task task) {
        return String.format("Got it. I've added this task\n    %s\nNow you have %d task(s) in the list.\n",
                task, tasks.getTaskListSize());
    }

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

    public String currentTaskListSize(TaskList tasks) {
        return String.format("Now you have " + tasks.getTaskListSize() + " tasks in the list");
    }
}
