package dukebot.tasklist;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task task = (Task) obj;
            if (taskType.equals(task.taskType)) {
                if (description.equals(task.description)) {
                    if ((dateTime == null && task.dateTime == null) || dateTime.equals(task.dateTime)) {
                        if (isDone == task.isDone) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    //...
}