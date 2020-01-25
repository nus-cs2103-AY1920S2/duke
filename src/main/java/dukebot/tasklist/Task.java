package dukebot.tasklist;

import dukebot.exception.DukeException;
import dukebot.ui.LineName;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public abstract class Task implements Serializable {
    private static final DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    protected final String description;

    private boolean isDone;
    protected final TaskType taskType;
    protected final LocalDateTime dateTime;

    protected Task(String description, TaskType taskType, LocalDateTime dateTime) {
        this.taskType = taskType;
        this.dateTime = dateTime;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task.
     */
    public static Task makeTask(String[] inp) throws DukeException {
        String taskType = inp[0];
        String description;
        String time;
        switch (taskType) {
        case "todo":
            description = String.join(" ", Arrays.copyOfRange(inp, 1, inp.length));
            if (description.length() == 0) {
                throw new DukeException(LineName.TODO_EMPTY);
            }
            return new Todo(description);
        case "deadline":
            int byInd = Arrays.asList(inp).indexOf("/by");
            if (byInd == inp.length - 1) {
                throw new DukeException(LineName.DEADLINE_EMPTY);
            } else if (byInd > 1) {
                description = String.join(" ", Arrays.copyOfRange(inp, 1, byInd));
                time = String.join(" ", Arrays.copyOfRange(inp, byInd + 1, inp.length));
                try {
                    LocalDateTime parsedDate = DateTimeParse.parseDate(time);
                    return new Deadline(description, parsedDate);
                } catch (DateTimeParseException e) {
                    throw new DukeException(LineName.DATE_TIME_PARSE_FAIL);
                }
            } else {
                throw new DukeException(LineName.DEADLINE_EMPTY);
            }
        case "event":
            int atInd = Arrays.asList(inp).indexOf("/at");
            if (atInd == inp.length - 1) {
                throw new DukeException(LineName.EVENT_EMPTY);
            } else if (atInd > 1) {
                description = String.join(" ", Arrays.copyOfRange(inp, 1, atInd));
                time = String.join(" ", Arrays.copyOfRange(inp, atInd + 1, inp.length));
                try {
                    LocalDateTime parsedDate = DateTimeParse.parseDate(time);
                    return new Event(description, parsedDate);
                } catch (DateTimeParseException e) {
                    throw new DukeException(LineName.DATE_TIME_PARSE_FAIL);
                }
            } else {
                throw new DukeException(LineName.EVENT_AT_MISSING);
            }
        default:
            //                This should never be triggered;
            throw new DukeException("Unknown task type");
        }
    }

    /**
     * Sets task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Get done status of task.
     */
    public boolean getDone() {
        return this.isDone;
    }

    /**
     * Get type of task.
     */
    public String getType() {
        return this.taskType.toString();
    }

    public String dateTimeToString() {
        if (dateTime == null) {
            return "";
        } else if (dateTime.toLocalDate().equals(LocalDate.now())) {
            return "Today " + dateTime.toLocalTime().toString();
        } else if (dateTime.compareTo(LocalDateTime.now()) < 0) {
            return this.dateTime.format(DEFAULT_FORMAT) + " [Over]";
        } else {
            return this.dateTime.format(DEFAULT_FORMAT);
        }

    }

    @Override
    public String toString() {
        return (description);
    }

    //...
}