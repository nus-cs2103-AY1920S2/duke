package duke.task;
import duke.storage.CSV;

public class ToDoTask extends Task {
    protected static final String TYPE_STR = "T";

    public ToDoTask(String name) {
        this(name, false);
    }

    private ToDoTask(String name, boolean done) {
        super(name, TaskType.TODO_TASK);
        this.done = done;
    }

    @Override
    public CSV toCSV() {
        return new CSV(new CSV(ToDoTask.TYPE_STR), new CSV(Boolean.toString(isDone())), new CSV(getName()));
    }

    public static ToDoTask parseFromCSV(CSV csv) {
        return new ToDoTask(csv.getStr(2), Boolean.parseBoolean(csv.getStr(1)));
    }

    @Override
    public String toString() {
        return sqB(ToDoTask.TYPE_STR) + sqB(gou()) + " " + getName();
    }
}