public class Event extends Task {

    protected TaskDate td;

    public Event(String desc, TaskDate td) {
        super(desc);
        this.td = td;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + td + ")";
    }
}