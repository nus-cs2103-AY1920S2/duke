public class Event extends Task {

    public Event (String date, String taskDescription) {

        super(date, taskDescription);

    }

    @Override
    public String toString() {

        return "[" + Types.Event + "]"
                + "[" + super.getStatus() + "]"
                + " " + super.getTaskDescription()
                + "(at:" + super.getDate() + ")";
    }

    @Override
    public Types getType() {

        return Types.Event;

    }
}
