public class Event extends AbstractTask {
    String date;

    public Event(String taskName, String date) {
        super(taskName);
        this.date = date.replaceFirst(" ", ": ");
    }

    @Override
    public String toString() {
        return "[E]" + taskStateString() + " " + this.taskName + "(" + this.date + ")";
    }

}