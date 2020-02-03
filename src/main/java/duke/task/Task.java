package duke.task;

public abstract class Task {
    static int size = 0;
    protected String description;
    private boolean isDone = false;
    protected String type = "task";
    private static final String TO_DO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";

    @Override
    public String toString() {
        return String.format("%s%s", this.isDone ? "[completed]" : "[uncompleted]", this.description);
    }

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatus() {
        return this.isDone ? "T" : "F";
    }

    public void setDone() throws Exception {
        if (isDone) {
            throw new Exception("You might have been mistaken. This task has already been completed.");
        }
        isDone = true;
    }

    protected static int getSize() {
        return size;
    }

    public static Task generateTask(String[] inputAsArray) throws Exception {
        String type = inputAsArray[0];
        switch (type.toLowerCase()) {
        case TO_DO: {
            return new TodoTask(inputAsArray);
        }
        case EVENT: {
            return new EventTask(inputAsArray);
        }
        case DEADLINE: {
            return new DeadlineTask(inputAsArray);
        }
        default:
            throw new Exception("You might have not chosen a valid task type! Valid types are events, todos, and deadlines");
        }
    }
}


