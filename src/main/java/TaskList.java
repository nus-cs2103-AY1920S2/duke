import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>();
        for (Task task: tasks) {
            this.tasks.add(task);
        }
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int getIndex) {
        return tasks.get(getIndex);
    }

    public Task delete(int deleteIndex) {
        return tasks.remove(deleteIndex);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Try to make a new to-do task from the input
     *
     * @param  input   the input which contains the to-do task information
     * @return  task    the new to-do task
     */
    public Todo makeNewTodoTask(String input) throws DukeException {
        String[] todoTokens = input.split(" ");

        // If there is a description
        if (todoTokens.length > 1) {
            return new Todo(input.replaceFirst("^todo ", ""));
        }

        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    /**
     * Try to make a new event task from the input
     *
     * @param  input   the input which contains the event task information
     * @return  task    the new event task
     */
    public Event makeNewEventTask(String input) throws DukeException {
        String[] eventTokens = input.split(" /at ");

        // If there is a description and a time/date
        if (eventTokens.length > 1) {
            String dateOrTime = eventTokens[1];
            String[] dateOrTimeTokens = dateOrTime.split(" ");

            // If there is a date and time
            if (dateOrTimeTokens.length <= 1) {
                throw new DukeException("☹ OOPS!!! Event tasks require a specific time and date.");
            }

            try {
                LocalDate date = LocalDate.parse(dateOrTimeTokens[0]);
                String description = eventTokens[0].replaceFirst("^event ", "");
                return new Event(description, date, dateOrTimeTokens[1]);
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Cannot parse date or time.");
            }
        }

        throw new DukeException("☹ OOPS!!! Event tasks require a description, and a specific time and date (e.g. 2019-12-12 2-4pm).");
    }

    /**
     * Try to make a new deadline task from the input
     *
     * @param  input   the input which contains the deadline task information
     * @return  task    the new deadline task
     */
    public Deadline makeNewDeadlineTask(String input) throws DukeException {
        String[] deadlineTokens = input.split(" /by ");

        // If there is a description and a deadline
        if (deadlineTokens.length > 1) {
            String dateOrTime = deadlineTokens[1];
            String[] dateOrTimeTokens = dateOrTime.split(" ");

            // If there is a date and time
            if (dateOrTimeTokens.length <= 1) {
                throw new DukeException("☹ OOPS!!! Deadline tasks require a specific time and date.");
            }

            try {
                LocalDate date = LocalDate.parse(dateOrTimeTokens[0]);
                LocalTime time = LocalTime.parse(dateOrTimeTokens[1], DateTimeFormatter.ofPattern("HHmm"));
                String description = deadlineTokens[0].replaceFirst("^deadline ", "");
                return new Deadline(description, date, time);
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Cannot parse date or time.");
            }
        }

        throw new DukeException("☹ OOPS!!! Deadline tasks require a description, and a specific time and date (e.g. 2019-12-12 1800).");
    }
}
