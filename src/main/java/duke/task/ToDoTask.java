package duke.task;

import duke.storage.CSV;

public class ToDoTask extends Task {
    protected static final String TYPE_STR = "T";

    /**
     * Main constructor used when creating this type of task
     *
     * @param name   = task name
     */
    public ToDoTask(String name) {
        this(name, false);
    }

    private ToDoTask(String name, boolean done) {
        super(name, TaskType.TODO_TASK);
        this.isDone = done;
    }

    /**
     * Convert all the relevant data to CSV in order to save to local file
     */
    @Override
    public CSV toCSV() {
        return new CSV(new CSV(ToDoTask.TYPE_STR), new CSV(Boolean.toString(isDone())), new CSV(getName()));
    }

    /**
     * Load task from local file
     *
     * @param csv = csv file parsed from local file
     * @return previously saved task
     */
    public static ToDoTask parseFromCSV(CSV csv) {
        return new ToDoTask(csv.getStr(2), Boolean.parseBoolean(csv.getStr(1)));
    }

    /**
     * String that describe the task when listing all the tasks
     */
    @Override
    public String toString() {
        return sqB(ToDoTask.TYPE_STR) + sqB(gou()) + " " + getName();
    }
}