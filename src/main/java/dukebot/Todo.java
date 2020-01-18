package dukebot;

public class Todo extends Task {

    protected Todo(String description) {
        super(description);
        this.taskType = TaskType.TODO;
    }
}

