public class ToDo extends Task {

    public ToDo(String date, String taskDescription) {
        super(date, taskDescription);
    }

    @Override
    public String toString() {
        status icon = null;

        if (super.isDone) {
            icon = status.Y;
        } else {
            icon = status.N;
        }

        return "[" + Types.ToDo + "]"
                + "[" + icon + "]"
                + " " + taskDescription;
    }
}
