public class Deadline extends Task {

    public Deadline(int index, String date, String taskDescription) {
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

        return "[" + Types.Deadline + "]"
                + "[" + icon + "]" + " "
                + this.getTaskDescription()
                + "(by:" + this.getDate() + ")";
    }
}
