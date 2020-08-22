package tasks;

public class Todo extends Task {
    public Todo(String taskDetails) {
        super(taskDetails);
    }

    @Override
    public String writeToFile() {
        return "T /" + super.writeToFile();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
