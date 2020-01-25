package dukebot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public abstract class Task {
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
    public static Task makeTask(String[] inp) throws InvalidTaskException {
        String taskType = inp[0];
        String description;
        String time;
        switch (taskType) {
        case "todo":
            description = String.join(" ", Arrays.copyOfRange(inp, 1, inp.length));
            if (description.length() == 0) {
                throw new InvalidTaskException("Duke doesn't see any description of the todo...");
            }
            return new Todo(description);
        case "deadline":
            int byInd = Arrays.asList(inp).indexOf("/by");
            if (byInd == inp.length - 1) {
                throw new InvalidTaskException("Duke doesn't see any deadline...");
            } else if (byInd > 1) {
                description = String.join(" ", Arrays.copyOfRange(inp, 1, byInd));
                time = String.join(" ", Arrays.copyOfRange(inp, byInd + 1, inp.length));
                try {
                    LocalDateTime parsedDate = DateTimeParse.parseDate(time);
                    return new Deadline(description, parsedDate);
                } catch (DateTimeParseException e) {
                    throw new InvalidTaskException("Master gave a date that Duke cannot read...");
                }
            } else {
                throw new InvalidTaskException("Master, use '/by' to indicate deadline,"
                        + " Duke wouldn't know otherwise...");
            }
        case "event":
            int atInd = Arrays.asList(inp).indexOf("/at");
            if (atInd == inp.length - 1) {
                throw new InvalidTaskException("Duke doesn't see any start time...");
            } else if (atInd > 1) {
                description = String.join(" ", Arrays.copyOfRange(inp, 1, atInd));
                time = String.join(" ", Arrays.copyOfRange(inp, atInd + 1, inp.length));
                try {
                    LocalDateTime parsedDate = DateTimeParse.parseDate(time);
                    return new Event(description, parsedDate);
                } catch (DateTimeParseException e) {
                    throw new InvalidTaskException("Master gave a date that Duke cannot read...");
                }
            } else {
                throw new InvalidTaskException("Master, use '/at' to indicate starting time,"
                        + " Duke wouldn't know otherwise...");
            }
        default:
            //                This should never be triggered;
            throw new InvalidTaskException("Unknown task type");
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
        } else if (dateTime.compareTo(LocalDateTime.now()) > 0) {
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