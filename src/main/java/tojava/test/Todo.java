package tojava.test;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type of task
     *
     * @return the letter indicating the task type
     */
    public String getType() {
        return "T";
    }

    /**
     * Returns the task date description
     *
     * @return task date description
     */
    public String getTask() {
        return description;
    }

    public String getDate() {
        return "";
    }

    public String getWord() {
        return "";
    }

    /**
     * Returns only the task description without the date
     * @return task description
     */
    public String getDescription() {
        return description;
    }

    public String changeDate() {
        return "";
    }

    public String changeTime() {
        return "";
    }

    public String getTime() {
        return "";
    }
}
