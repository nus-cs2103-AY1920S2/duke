package ui;

import exception.CommandNotFoundException;
import exception.EmptyTaskListException;
import exception.InvalidIndexException;
import task.Task;
import task.TaskList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
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
     * Prints exit message.
     */
    public String getExitMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Prints list of all tasks.
     *
     * @param tasks array list of tasks.
     */
    public String listAllTasks(TaskList tasks) throws EmptyTaskListException {
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
     * Prints unknown command.
     *
     * @throws CommandNotFoundException if command not recognised.
     */
    public String printUnknownCommand() throws CommandNotFoundException {
        throw new CommandNotFoundException("We don't recognise this command!!!!\n");
    }

    /**
     * Acknowledges when the task(s) has been completed.
     *
     * @param tasks array list of current tasks
     * @param arrayOfIndexes varargs for inputted index(es) of task to mark as complete.
     * @throws InvalidIndexException if any of the index is invalid
     */
    public String acknowledgeDone(TaskList tasks, int... arrayOfIndexes) throws InvalidIndexException {
        int[] arrayOfDoneIndexes = IntStream.of(arrayOfIndexes)
                                            .filter(index -> tasks.getTaskListSize() > index && index >= 0)
                                            .toArray();

        if (arrayOfDoneIndexes.length == 0) { throw new InvalidIndexException("Index does not match any task!\n"); }
        String doneResponse = "Nice! I've marked this task(s) as done:\n";

        IntStream.of(arrayOfDoneIndexes)
                 .forEach(index -> tasks.getTask(index).markAsDone());

        List<String> listOFPrintedDoneTasks =
                IntStream.of(arrayOfDoneIndexes)
                         .mapToObj(index -> String.format("%d.  %s\n", index + 1, tasks.getTask(index)))
                         .collect(Collectors.toList());

        listOFPrintedDoneTasks.add(0, doneResponse);

        return String.join("", listOFPrintedDoneTasks);

    }


    /**
     * Acknowledges once todo task is added.
     *
     * @param tasks array list of tasks.
     * @param todo index of which task is done.
     */
    public String acknowledgeTodo(TaskList tasks, Task todo) {
        if (tasks.checkDuplicate(todo)) {
            return "Hey man! This todo task already exists in the list. You don't wanna duplicate!\n";
        } else {
            tasks.addTask(todo);
            return String.format("Got it. I've added this task\n    %s\nNow you have %d task(s) in the list.\n",
                    todo, tasks.getTaskListSize());
        }
    }

    /**
     * Acknowledges once deadline task is added.
     *
     * @param tasks array list of tasks.
     * @param deadline time of deadline.
     */
    public String acknowledgeDeadline(TaskList tasks, Task deadline) {
        if (tasks.checkDuplicate(deadline)) {
            String s = "Note!! This task action already exists in the list!\n";
            return s + String.format("Now you have %d tasks in the list.\n", tasks.getTaskListSize());
        } else {
            tasks.addTask(deadline);
            return String.format("Got it. I've added this task\n    %s\nNow you have %d task(s) in the list.\n",
                    deadline, tasks.getTaskListSize());
        }
    }

    /**
     * Acknowledges once event task is added.
     *
     * @param tasks array list of tasks.
     * @param event time of event.
     */
    public String acknowledgeEvent(TaskList tasks, Task event) {
        if (tasks.checkDuplicate(event)) {
            String s = "Note!! This task action already exists in the list!\n";
            return s + String.format("Now you have %d tasks in the list.\n", tasks.getTaskListSize());
        } else {
            tasks.addTask(event);
            return String.format("Got it. I've added this task\n    %s\nNow you have %d task(s) in the list.\n",
                    event, tasks.getTaskListSize());
        }
    }

    /**
     * Acknowledges once task is deleted.
     *
     * @param tasks array list of tasks.
     * @param arrayOfIndexes integer array of task indexes to be deleted.
     * @throws InvalidIndexException when index is out of bound.
     */
    public String acknowledgeDelete(TaskList tasks, int... arrayOfIndexes) throws InvalidIndexException {
        String s = "Noted. I've removed this task(s):\n";
        int[] arrayOfDeleteIndexes = IntStream.of(arrayOfIndexes)
                                              .filter(index -> tasks.getTaskListSize() > index && index >= 0)
                                              .toArray();

        if (arrayOfDeleteIndexes.length == 0) { throw new InvalidIndexException("Index does not match any task!\n"); }

        List<String> listOFPrintedDeleteTasks =
                IntStream.of(arrayOfDeleteIndexes)
                         .mapToObj(index -> String.format("%d.  %s\n", index + 1, tasks.getTask(index)))
                         .collect(Collectors.toList());

        tasks.removeTasks(arrayOfDeleteIndexes);
        String taskSizeMessage = String.format("Now you have " + tasks.getTaskListSize() + " tasks in the list");
        listOFPrintedDeleteTasks.add(0, s);
        listOFPrintedDeleteTasks.add(taskSizeMessage);

        return String.join("", listOFPrintedDeleteTasks);
    }

    /**
     * Acknowledges when the task is searched.
     *
     * @param tasks array list of tasks.
     * @param taskSearchKey String task to search for.
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
}
