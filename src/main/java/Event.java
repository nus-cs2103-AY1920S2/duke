public class Event extends Task {

    protected String by;
    public String type;

    public Event(String description, String by) {
        super(description);
        this.by = by;
        this.type = "event";
    }

    @Override
    public String getBy() {

        return by;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {

        if (super.getStatus() == 0) {
            return "[E][✗]" + super.toString() + " (at:" + by + ")";
        } else {
            return "[E][✓]" + super.toString() + " (at:" + by + ")";
        }
    }
}