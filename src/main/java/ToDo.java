public class ToDo extends Task{
    //tasks without any date/time attached to it
    // e.g., visit new theme park
    private TaskType type = TaskType.TODO;
    protected String time = "";

    public ToDo(String description, String time) {
        super(description, time);
    }

    public TaskType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
