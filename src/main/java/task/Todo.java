package task;

public class Todo extends Task {
    public Todo(String description) {
        super(Constant.TODO.getType(), description);
    }

    public Todo(String[] fromMemory) {
        super(Constant.TODO.getType(), fromMemory[1], fromMemory[2]);
    }
}
