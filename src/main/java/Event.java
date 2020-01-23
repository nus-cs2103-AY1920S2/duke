public class Event extends Task {

    public Event (int index, String date, String taskDescription) {
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

        return "[" + Types.Event + "]"
                + "[" + icon + "]"
                + " " + this.getTaskDescription()
                + "(at:" + this.getDate() + ")";
    }
}
