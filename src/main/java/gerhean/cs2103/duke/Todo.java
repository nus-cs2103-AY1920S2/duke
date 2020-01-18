package gerhean.cs2103.duke;

public class Todo extends Task {

    protected Todo(String description) {
        super(description);
        this.taskType = TaskType.TODO;
    }
}

