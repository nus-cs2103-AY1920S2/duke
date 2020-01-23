public class Deadline extends Task {

    public Deadline(String date, String taskDescription) {
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

        return "[" + Types.Deadline + "]"
                + "[" + icon + "]" + " "
                + this.getTaskDescription()
                + "(by:" + this.getDate() + ")";
    }
}
