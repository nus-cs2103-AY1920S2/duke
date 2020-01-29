package seedu.duke;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains the task list and operations to add, delete, and change the list.
 */
public class TaskList {
    static List<Task> tasks;
    protected Storage storage;

    /**
     * Constructor for TaskList.
     *
     * @param tasks the list of tasks
     * @param storage the hard disk for storage of data
     */
    public TaskList(List<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Adds the user input of todo task into the task list.
     *
     * @param desc the details of the todo task
     * @param doneStatus an indicator which shows whether a todo task has been completed or not
     * @throws IOException if an input or output exception occurred
     */
    protected void addTodo(String desc, String doneStatus) throws IOException {
        Task todo = new Todo(desc);
        if (doneStatus.equalsIgnoreCase("Y")) {
            todo.markAsDone();
        }
        tasks.add(todo);
        storage.addToStorage(todo);
        printAddToList();
        System.out.println(todo.toString());
        printNumTask();
    }

    /**
     * Adds the user input of deadline task into the task list.
     *
     * @param desc the details of the deadline task
     * @param doneStatus an indicator which shows whether a deadline task has been completed or not
     * @throws InvalidTaskInputException if an invalid task command is input
     * @throws IOException if an input or output exception occurred
     * @throws InvalidDateException if a date is input in a wrong format
     */
    protected void addDeadline(String desc, String doneStatus)
            throws InvalidTaskInputException, IOException, InvalidDateException {
        String[] descs = desc.split(" /by |\\|");
        if (descs.length == 1) { // invalid Deadline input format
            throw new InvalidTaskInputException();
        }

        String deadlineDesc = descs[0].trim();
        String deadlineTime = descs[1].trim();
        LocalDate formattedDeadlineTime = null;
        if (isValidDate(deadlineTime)) {
            formattedDeadlineTime = LocalDate.parse(deadlineTime);
        } else {
            throw new InvalidDateException();
        }
        Task deadline = new Deadline(deadlineDesc, formattedDeadlineTime);

        if (doneStatus.equalsIgnoreCase("Y")) {
            deadline.markAsDone();
        }
        tasks.add(deadline);
        storage.addToStorage(deadline);
        printAddToList();
        System.out.println(deadline.toString());
        printNumTask();
    }

    /**
     * Adds the user input of event task into the task list.
     *
     * @param desc the details of the event task
     * @param doneStatus an indicator which shows whether an event task has been completed or not
     * @throws InvalidTaskInputException if an invalid task command is input
     * @throws IOException if an input or output exception occurred
     * @throws InvalidDateException if a date is input in a wrong format
     */
    protected void addEvent(String desc, String doneStatus)
            throws InvalidTaskInputException, IOException, InvalidDateException {
        String[] descs = desc.split(" /at |\\|");
        if (descs.length == 1) { // invalid Event input format
            throw new InvalidTaskInputException();
        }
        String eventDesc = descs[0].trim();
        String eventTime = descs[1].trim();
        LocalDate formattedEventTime = null;
        if (isValidDate(eventTime)) {
            formattedEventTime = LocalDate.parse(eventTime);
        } else {
            throw new InvalidDateException();
        }
        Task event = new Event(eventDesc, formattedEventTime);

        if (doneStatus.equalsIgnoreCase("Y")) {
            event.markAsDone();
        }

        tasks.add(event);
        storage.addToStorage(event);
        printAddToList();
        System.out.println(event.toString());
        printNumTask();
    }

    /**
     * Checks if an input date is written in a valid date format.
     *
     * @param inDate the input date
     * @return true if the input date is written in a valid date format
     */
    public boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    /**
     * Prints the list of tasks the user has.
     */
    public void printList(List<Task> currTasks) {
        if (currTasks.size() == 0) {
            System.out.println("You currently don't have any task. Start listing now!");
        } else {
            System.out.println("Stop procrastinating. Do it now!");
            for (int i = 0; i < currTasks.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, currTasks.get(i).toString());
            }
        }
    }

    public void printList() {
        printList(tasks);
    }

    /**
     * Marks the task as done by changing the done status from "N" to "Y".
     * It also updates the done status in the hard disk storage list accordingly.
     *
     * @param index the index number of the task that is marked as done
     * @throws IOException if an input or output exception occurred
     */
    public void markTaskAsDone(int index) throws IOException {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        storage.changeToStorage(index);
        System.out.println("Good job! One off your chest!");
        System.out.println(task.toString());
    }

    /**
     * Deletes the task from the task list and the hard disk storage list accordingly.
     *
     * @param index the index number of the task that is being deleted
     * @throws IOException if an input or output exception occurred
     */
    public void deleteTask(int index) throws IOException {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        printRemoveTask();
        System.out.println(task.toString());
        printNumTask();
        storage.deleteInStorage(index);
    }

    public void findTask(String desc) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(desc)) {
                foundTasks.add(task);
            }
        }

        if (foundTasks.size() == 0) {
            printNoFoundTask();
        } else {
            printFoundTask();
            printList(foundTasks);
        }
    }

    public void printFoundTask() {
        System.out.println("Here are the matching tasks in your list:");
    }

    public void printNoFoundTask() {
        System.out.println("Sorry I can't find what you are looking for....");
    }

    private static void printRemoveTask() {
        System.out.println("Okay, I have removed this task for you:");
    }

    private void printAddToList() {
        System.out.println("Gotcha. Added this to your list:");
    }

    private void printNumTask() {
        String taskWord;
        if (tasks.size() <= 1) {
            taskWord = "task";
        } else {
            taskWord = "tasks";
        }
        System.out.printf("Now you got %d %s in your list!\n", tasks.size(), taskWord);
    }
}
