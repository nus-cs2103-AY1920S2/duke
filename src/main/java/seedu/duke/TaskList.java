package seedu.duke;

import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;

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
    protected List<Task> tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for TaskList.
     *
     * @param tasks The list of tasks.
     * @param storage The hard disk for storage of data.
     */
    public TaskList(List<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
        ui = new Ui();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTasks(Task task) {
        tasks.add(task);
    }

    /**
     * Adds the user input of todo task into the task list.
     *
     * @param desc The details of the todo task.
     * @param doneStatus An indicator which shows whether a todo task has been completed or not.
     * @throws IOException If an input or output exception occurred.
     */
    protected void addTodo(String desc, String doneStatus) throws IOException {
        Task todo = new Todo(desc);
        if (doneStatus.equalsIgnoreCase("Y")) {
            todo.markAsDone();
            assert todo.isDone();
        }
        tasks.add(todo);
        storage.addToStorage(todo);
        ui.printAddToList();
        ui.print(todo.toString());
        ui.printNumTask(tasks);
    }

    /**
     * Adds the user input of deadline task into the task list.
     *
     * @param desc The details of the deadline task.
     * @param doneStatus An indicator which shows whether a deadline task has been completed or not.
     * @throws InvalidTaskInputException If an invalid task command is input.
     * @throws IOException If an input or output exception occurred.
     * @throws InvalidDateException If a date is input in a wrong format.
     */
    protected void addDeadline(String desc, String doneStatus)
            throws InvalidTaskInputException, IOException, InvalidDateException {
        String[] descs = desc.split(" /by |\\|");
        if (descs.length == 1) { // invalid Deadline input format
            throw new InvalidTaskInputException();
        }

        String deadlineDesc = descs[0].trim();
        String deadlineDate = descs[1].trim();
        LocalDate formattedDeadlineDate = null;
        if (isValidDate(deadlineDate)) {
            formattedDeadlineDate = LocalDate.parse(deadlineDate);
        } else {
            throw new InvalidDateException();
        }
        Task deadline = new Deadline(deadlineDesc, formattedDeadlineDate);

        if (doneStatus.equalsIgnoreCase("Y")) {
            deadline.markAsDone();
            assert deadline.isDone();
        }
        tasks.add(deadline);
        storage.addToStorage(deadline);
        ui.printAddToList();
        ui.print(deadline.toString());
        ui.printNumTask(tasks);
    }

    /**
     * Adds the user input of event task into the task list.
     *
     * @param desc The details of the event task.
     * @param doneStatus An indicator which shows whether an event task has been completed or not.
     * @throws InvalidTaskInputException If an invalid task command is input.
     * @throws IOException If an input or output exception occurred.
     * @throws InvalidDateException If a date is input in a wrong format.
     */
    protected void addEvent(String desc, String doneStatus)
            throws InvalidTaskInputException, IOException, InvalidDateException {
        String[] descs = desc.split(" /at |\\|");
        if (descs.length == 1) { // invalid Event input format
            throw new InvalidTaskInputException();
        }
        String eventDesc = descs[0].trim();
        String eventDate = descs[1].trim();
        LocalDate formattedEventDate = null;
        if (isValidDate(eventDate)) {
            formattedEventDate = LocalDate.parse(eventDate);
        } else {
            throw new InvalidDateException();
        }
        Task event = new Event(eventDesc, formattedEventDate);

        if (doneStatus.equalsIgnoreCase("Y")) {
            event.markAsDone();
            assert event.isDone();
        }

        tasks.add(event);
        storage.addToStorage(event);
        ui.printAddToList();
        ui.print(event.toString());
        ui.printNumTask(tasks);
    }

    /**
     * Checks if an input date is written in a valid date format.
     *
     * @param inDate The input date.
     * @return true if the input date is written in a valid date format.
     */
    private boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public void printList() {
        ui.printList(tasks);
    }

    /**
     * Marks the task as done by changing the done status from "N" to "Y".
     * It also updates the done status in the hard disk storage list accordingly.
     *
     * @param index The index number of the task that is marked as done.
     * @throws IOException If an input or output exception occurred.
     */
    public void markTaskAsDone(int index) throws IOException {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        assert task.isDone();
        storage.changeToStorage(index);
        ui.printTaskDone();
        ui.print(task.toString());
    }

    /**
     * Deletes the task from the task list and the hard disk storage list accordingly.
     *
     * @param index The index number of the task that is being deleted.
     * @throws IOException If an input or output exception occurred.
     */
    public void deleteTask(int index) throws IOException {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        ui.printRemoveTask();
        ui.print(task.toString());
        ui.printNumTask(tasks);
        storage.deleteInStorage(index);
    }

    /**
     * Finds task(s) which contains a substring given by the user.
     *
     * @param desc A substring of a task that user wants to find.
     */
    public void findTask(String desc) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(desc)) {
                foundTasks.add(task);
            }
        }

        if (foundTasks.size() == 0) {
            ui.printNoFoundTask();
        } else {
            assert foundTasks.size() > 0 : foundTasks.size();
            ui.printFoundTask();
            ui.printList(foundTasks);
        }
    }
}
