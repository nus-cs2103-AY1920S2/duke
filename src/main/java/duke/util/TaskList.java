package duke.util;

import duke.exception.InvalidDateException;
import duke.exception.InvalidIndexException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

/**
 * Represents the task list used by Duke to track tasks.
 *
 * <p>CS2103T AY19/20 Semester 2
 * Individual Duke project
 * 30 Jan 2020
 *
 * @author Jel
 */
public class TaskList {
    protected Ui ui;
    protected Storage storage;
    protected List<Task> tasks;

    /**
     * Constructs a TaskList instance.
     * @param storage The storage instance that will the program will extract data from and store all data to.
     */
    public TaskList(Storage storage, Ui ui) {
        this.storage = storage;
        this.tasks = storage.tasks;
        this.ui = ui;
    }

    /**
     * Lists all tasks in the current list of tasks.
     */
    protected String listTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("There are currently %d tasks. Below is your task list:\n", tasks.size()));
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\t").append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Adds a new todo to the task list and the data file.
     * @param desc The description of details of the todo.
     * @return The string signifying successful addition of task.
     * @throws IOException Error opening file where data is to be added.
     */
    protected String addTodo(String desc) throws IOException {
        Task toSave = new Todo(desc);
        tasks.add(toSave);
        storage.saveTask(toSave, true);
        return confirmAddition();
    }

    /**
     * Checks if the date provided has a valid format and is a valid date.
     * @param source The date to be checked.
     * @return The boolean representing whether the date provided is valid.
     */
    private static boolean isValidDate(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(source);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Adds a new deadline or event to the task list and data file.
     * @param id The unique id identifying the type of task to be added.
     * @param descAndDate The description or details of the deadline or event.
     * @return The string signifying successful addition of task.
     * @throws IOException Error opening file where data is to be added.
     * @throws InvalidDateException Date provided is of the wrong format or invalid.
     */
    protected String addDeadlineOrEvt(String id, String descAndDate) throws IOException, InvalidDateException {
        assert id.equals(" /by ") || id.equals(" /at ") : "Invalid id supplied.";
        String[] temp = descAndDate.split(" \\| ");
        if (isValidDate(temp[1])) {
            if (id.equals(" /by ")) {
                Task toSave = new Deadline(temp[0].trim().toString(), LocalDate.parse(temp[1]));
                storage.saveTask(toSave, true);
                tasks.add(toSave);
            } else {
                Task toSave = new Event(temp[0].trim().toString(), LocalDate.parse(temp[1]));
                storage.saveTask(toSave, true);
                tasks.add(toSave);
            }
            return confirmAddition();
        } else {
            throw new InvalidDateException();
        }
    }

    /**
     * Prints out confirmation of addition of new task to list and data file.
     */
    protected String confirmAddition() {
        StringBuilder sb = new StringBuilder("Got it. I've added this task:\n\t");
        int size = this.tasks.size();
        sb.append(this.tasks.get(size - 1)).append(String.format("\nYou now have %d %s in the list.\n", size,
                size > 1 ? "tasks" : "task"));
        return sb.toString();
    }

    /**
     * Deletes the specified task from the list and data file.
     * @param taskNum The task number for the task to be deleted.
     * @throws InvalidIndexException Task number or index provided is out of bound of current list of tasks.
     * @throws IOException Error opening file where data is to be deleted.
     */
    protected String deleteTask(int taskNum) throws InvalidIndexException, IOException {
        int size = tasks.size();
        StringBuilder sb = new StringBuilder();
        if (isValidIndex(taskNum, size)) {
            sb.append("Alright, I've removed this task.\n\t" + tasks.get(taskNum - 1));
            tasks.remove(taskNum - 1);
            sb.append(String.format("\nYou now have %d %s in the list.\n", size - 1,
                    (size - 1 > 1 ? "tasks" : "task")));
            storage.clearAllData();
            storage.updateData();
            return sb.toString();
        } else {
            throw new InvalidIndexException(taskNum, size);
        }
    }

    /**
     * Marks the specified task as done in the list and data file.
     * @param taskNum The task number for the task to be marked as done.
     * @throws InvalidIndexException Task number or index provided is out of bound of current list of tasks.
     * @throws IOException Error opening file where data is to be marked as done.
     */
    protected String markTaskAsDone(int taskNum) throws InvalidIndexException, IOException {
        int size = tasks.size();
        StringBuilder sb = new StringBuilder();
        if (isValidIndex(taskNum, size)) {
            Task t = tasks.get(taskNum - 1);
            if (t.isDone) {
                System.out.println("done");
                sb.append("This task was already marked as done.\n");
                sb.append(t).append("\n");
                storage.clearAllData();
                storage.updateData();
            } else {
                t.markAsDone();
                sb.append("Great job! I've marked this task as done:\n\t").append(t).append("\n");
                storage.clearAllData();
                storage.updateData();
            }
            return sb.toString();
        } else {
            throw new InvalidIndexException(taskNum, size);
        }
    }

    protected String markTaskNotDone(int taskNum) throws InvalidIndexException, IOException {
        int size = tasks.size();
        StringBuilder sb = new StringBuilder();
        if (isValidIndex(taskNum, size)) {
            Task t = tasks.get(taskNum - 1);
            if (t.isDone) {
                t.markAsNotDone();
                sb.append("Ok, I've marked this task as not done:\n\t").append(t).append("\n");
                storage.clearAllData();
                storage.updateData();
            } else {
                sb.append("This task has not yet been completed.\n\t").append(t).append("\n");
            }
            return sb.toString();
        } else {
            throw new InvalidIndexException(taskNum, size);
        }
    }

    private boolean isValidIndex(int taskNum, int size) {
        return (taskNum > 0 && taskNum <= size);
    }

    /**
     * Finds tasks with that details match the keyword provided.
     * @param keyword The keyword user is searching for.
     */
    protected String findTask(String keyword) {
        int count = 0;
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n\t");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                count++;
                sb.append(tasks.get(i)).append("\n\t");
            }
        }
        if (count > 0) {
            return sb.toString().trim() + "\n";
        }
        return String.format("There are no matching tasks for the keyword '%s'.", keyword);
    }
}
