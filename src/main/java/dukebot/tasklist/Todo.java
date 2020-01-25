package dukebot.tasklist;

public class Todo extends Task {

    protected Todo(String description) {
        super(description, TaskType.TODO,null);
    }
}
