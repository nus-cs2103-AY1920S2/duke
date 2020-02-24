package task;

public class Todo extends Task {

    public Todo(String command) {
        super(command);
    }

    @Override
    public String toStringTxt() {
        return "T/" + super.getIcon() + "/" + command;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}