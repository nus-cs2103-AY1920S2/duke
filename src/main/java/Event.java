public class Event extends Task{
    String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        totalTasks++;
    }

    public String getType() {
        return "[E]";
    }

    @Override
    public void taskSummary() {
        System.out.println(getType() + getStatusIcon() + " " + description + " (at: " + at + ")");
    }

    public String saveString() {
        return "E|" + (isDone? "1|" : "0|") + description + "|" + at;
    }
}
