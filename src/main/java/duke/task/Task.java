package duke.task;

import duke.Copyable;
import duke.storage.CSV;
import duke.storage.CSVParsable;

public abstract class Task implements CSVParsable, Copyable {
    protected String name;
    protected boolean isDone = false;
    protected TaskType type;
    protected boolean isScrapped = false;

    protected Task(String name, TaskType type) {
        this.name = name;
        this.type = type;
    }

    protected Task(String name, boolean isDone, TaskType type, boolean isScrapped) {
        this.name = name;
        this.isScrapped = isScrapped;
        this.isDone = isDone;
        this.type = type;
    }

    /**
     * getter for done
     *
     * @return done
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * set done to true
     *
     * @return true
     */
    public boolean setToDone() {
        return this.isDone = true;
    }

    /**
     * set done to false
     *
     * @return false
     */
    public boolean setToNotDone() {
        return this.isDone = false;
    }

    /**
     * scrap this task
     *
     * @return true
     */
    public boolean scrapTask() {
        return this.isScrapped = true;
    }

    /**
     * scrap this task
     *
     * @return is the task scrapped?
     */
    public boolean isScrapped() {
        return this.isScrapped;
    }

    /**
     * getter for name
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * getter for task type
     *
     * @return type
     */
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

    /**
     * Parse tasks from csv object
     *
     * @param csv = csv that described the task (parsed from local file)
     * @return previously saved task
     */
    public static Task parseFromCSV(CSV csv) {
        switch (csv.getStr(0)) {
            case ToDoTask.TYPE_STR:
                return ToDoTask.parseFromCSV(csv);
            case DeadlineTask.TYPE_STR:
                return DeadlineTask.parseFromCSV(csv);
            case EventTask.TYPE_STR:
                return EventTask.parseFromCSV(csv);
            default:
                return new Task(csv.getStr(csv.size()), TaskType.TODO_TASK) {
                    public Task getCopy() {
                        return this;
                    }
                };
        }
    }

    public abstract Task getCopy();
}