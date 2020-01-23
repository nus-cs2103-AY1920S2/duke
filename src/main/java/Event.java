public class Event extends Task {

    public Event (String date, String taskDescription) {
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

        return "[" + Types.Event + "]"
                + "[" + icon + "]"
                + " " + this.getTaskDescription()
                + "(at:" + this.getDate() + ")";
    }
}
