public class Event extends Task {

    // for the date and time option
    private String at;

     Event(String description, String at) {

        super(description);
        this.at = at;
    }

     void setAt(String s) {
        this.at = s.substring(s.indexOf("at ")).replaceAll("at", "");
    }

    @Override
    void setDescription(String s) {
        String event_task = s.substring(s.indexOf("event"), s.indexOf("/"));
        String event_task2= event_task.replaceAll("event", "").trim();
        super.setDescription(event_task2);
    }

    @Override
    public String toString() {
        return " [E]" + super.toString() + " (at: " + at + ")";
    }
}
