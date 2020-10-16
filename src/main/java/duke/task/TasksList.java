package duke.task;

import duke.Duke;
import duke.exception.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A Collection to manage all the Task classes and subclasses. Provides
 * arrayList like functionality to more easily manipulate data.
 */
public class TasksList {
    public static final String TODO_FORMAT = "The todo format is: todo <desc>";
    public static final String EVENT_FORMAT = "The event format is: event <desc> /at <time>";
    public static final String DEADLINE_FORMAT = "The deadline format is: deadline <desc> /by <time>";
    public static final String TASK_NOT_FOUND = "Task not found";
    public static final String DATE_FORMAT = "The date formatting is : YYYY-MM-DD, use leading zeroes to pad for missing digits";

    public static final int NUM_ARGS_DEADLINE = 2;
    public static final int NUM_ARGS_EVENT = 2;

    public  ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Prints the number of elements currently in the ArrayList<>
     */
    public int size(){
        return tasks.size();
    }

    /**
     * Creates and adds a Todo to the ArrayList with specified details in the arguments.
     *
     * @param description the details of the task.
     * @param isDone marks Todo as done if true and vice versa.
     * @return The object just created if successful else it will throw an exception.
     * @throws DukeException  If the format add command is wrong
     */
    public Todo addTodo(String description, boolean isDone) throws DukeException{
        if (description == null) {
            throw new DukeException(TODO_FORMAT);
        }

        Todo newTask = new Todo(description, isDone);
        tasks.add(newTask);

        return newTask;
    }

    /**
     * Creates and adds a Deadline to the ArrayList with specified details in the arguments.
     *
     * @param description the details of the task includes the due date.
     * @param isDone marks Deadline as done if true and vice versa.
     * @return The object just created if successful else it will throw an exception.
     * @throws DukeException  If the format add command is wrong
     */
    public Deadline addDeadline(String description, boolean isDone) throws DukeException{
        String[] taskDetails = description.split(" /by ");
        if(taskDetails.length < NUM_ARGS_DEADLINE) {
            throw new DukeException(DEADLINE_FORMAT);
        }

        LocalDate date = getDateString(taskDetails[1]);
        Deadline newTask = new Deadline(taskDetails[0], date, isDone);
        tasks.add(newTask);

        return newTask;
    }

    /**
     * Creates and adds a Event to the ArrayList with specified details in the arguments.
     *
     * @param description the details of the task includes the start date.
     * @param isDone marks Event as done if true and vice versa.
     * @return The object just created if successful else it will throw an exception.
     * @throws DukeException  If the format add command is wrong
     */
    public Event addEvent(String description, boolean isDone) throws DukeException{
        String[] taskDetails = description.split(" /at ");
        if (taskDetails.length < NUM_ARGS_EVENT) {
            throw new DukeException(EVENT_FORMAT);
        }

        LocalDate date = getDateString(taskDetails[1]);
        Event newTask = new Event(taskDetails[0], date, isDone);

        tasks.add(newTask);

        return newTask;
    }

    /**
     * Deleted the first task with a matching description field(not name)
     *
     * @param description the details in the task to be deleted.
     * @return A copy of the object just deleted
     * @throws DukeException  If the task to delete is not found
     */
    public Task delete(String description) throws DukeException{
        for (Task task: tasks) {
            if (task.description.equals(description)) {
                tasks.remove(task);
                return task;
            }
        }
        throw new DukeException(TASK_NOT_FOUND);
    }

    // Tries to find a date in str to convert and return as a LocalDate object
    public LocalDate getDateString(String str) throws DukeException {
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}"); // Looks for YYYY-MM-DD or YYYY-DD-MM formatting
        Matcher matcher = pattern.matcher(str);

        boolean isDateFound = matcher.find();

        if (isDateFound) {
            try {
                String match = matcher.group();
                return LocalDate.parse(match);
            } catch (DateTimeParseException e) {
                throw new DukeException(DATE_FORMAT);
            }
        }

        throw new DukeException(DATE_FORMAT);
    }
}
