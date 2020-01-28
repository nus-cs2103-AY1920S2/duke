public class Todo extends Task {

    public Todo(String description, String tasktype) {
        super(description, tasktype);
    }

    @Override
    public String getDeadline() {
        return "";
    }

}