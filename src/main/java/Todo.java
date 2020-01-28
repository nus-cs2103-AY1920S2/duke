public class Todo extends Task {

    public Todo(String description, String tasktype, String raw_input) {
        super(description, tasktype, raw_input);
    }

    @Override
    public String getDeadline() {
        return "";
    }

}