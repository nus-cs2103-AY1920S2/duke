public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public static Task createStartingTask(String[] taskParams) {
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
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] "
                + description;
    }
}
