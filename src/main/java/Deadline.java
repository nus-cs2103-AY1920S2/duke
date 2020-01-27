public class Deadline extends Task {

    public Deadline(String date, String taskDescription) {

        super(date, taskDescription);

    }

    @Override
    public String toString() {

        return "[" + Types.Deadline + "]"
                + "[" + super.getStatus() + "]"
                + " " + super.getTaskDescription()
                + "(by:" + super.getDate() + ")";
    }

    @Override
    public Types getType() {

        return Types.Deadline;

    }
}
