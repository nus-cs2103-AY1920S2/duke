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
    void setDescription(String s) throws DukeException {

        try {
            String event_task = s.substring(s.indexOf("event"), s.indexOf("/"));
            String event_task2 = event_task.replaceAll("event", "").trim();
            super.setDescription(event_task2);
        } catch (Exception e) {
            throw new DukeException("", "Event");
        }
    }

    @Override
    Task_Codes getTaskCodes() {
        return Task_Codes.E;
    }

    @Override
    public String toString() {
        return " [" + Task_Codes.E + "]" + super.toString() + " (at: " + at + ")";
    }
}
