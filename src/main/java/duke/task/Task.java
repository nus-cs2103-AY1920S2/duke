package duke.task;
import duke.storage.CSV;
import duke.storage.CSVParsable;

public abstract class Task implements CSVParsable {
    private String name;
    protected boolean done = false;
    protected TaskType type;

    protected Task(String name, TaskType type) {
        this.name = name;
        this.type = type;
    }

    public boolean isDone() {
        return this.done;
    }

    public boolean setToDone() {
        return this.done = true;
    }

    public String getName() {
        return this.name;
    }

    public TaskType getType() {
        return this.type;
    }

    protected String gou() {
        return isDone() ? "✓" : "✗";
    }

    protected String sqB(String s) {
        return "[" + s + "]";
    }

    public CSV toCSV() {
        return new CSV(new CSV("T"), new CSV(Boolean.toString(isDone())), new CSV(getName()));
    }

    public static Task parseFromCSV(CSV csv) {
        switch (csv.getStr(0)) {
        case ToDoTask.TYPE_STR:
            return ToDoTask.parseFromCSV(csv);
        case DeadlineTask.TYPE_STR:
            return DeadlineTask.parseFromCSV(csv);
        case EventTask.TYPE_STR:
            return EventTask.parseFromCSV(csv);
        default:
            return new Task(csv.getStr(csv.size()), TaskType.TODO_TASK) {};
        }
    }
}