public class Event extends Task{
    public Event(String msg, String date) {
        super(msg);
        super.type = "[E]";
        super.time = "(at: " + date + ")";
    }
}
