public class ToDo extends Task {

    public ToDo(int index, String date, String taskDescription) {
        super(index, date, taskDescription);
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
