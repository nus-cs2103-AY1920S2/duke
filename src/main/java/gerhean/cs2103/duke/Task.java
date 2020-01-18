package gerhean.cs2103.duke;

import java.util.Arrays;

public class Task {
    protected String description;
    private boolean isDone;
    protected TaskType taskType;

    protected Task(String description) {
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
                return new Deadline(description, time);
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
                return new Event(description, time);
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

    @Override
    public String toString() {
        return (description);
    }

    //...
}