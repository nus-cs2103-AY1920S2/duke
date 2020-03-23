/**
 * Represents a Task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Creates a task using the .txt file at the start of the programme
     * @param taskParams list of tasks as string of params
     * @return New task added to Task List
     * @throws RuntimeException esception
     */
    public static Task createStartingTask(String[] taskParams) throws RuntimeException {
        String type = taskParams[0];
        boolean isDone = taskParams[1].equals("true");
        String description = taskParams[2];
        if (type.equals("T")) {
            return new ToDos(description, isDone);

        } else if (type.equals("D")) {
            String by = taskParams[3];
            return new Deadlines(description, isDone, by);

        } else if (type.equals("E")) {
            String at = taskParams[3];
            return new Events(description, isDone, at);

        } else {
            return null;
        }
    }

    public boolean containsSubstring(String search) {
        return description.contains(search);
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : "O");
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks a task as done.
     */
    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] "
                + description;
    }
}
