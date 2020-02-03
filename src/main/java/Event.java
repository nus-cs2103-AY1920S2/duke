public class Event extends task {
    private String date;

    public Event (String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toSave() {
        if (this.isDone()) {
            return "E /n 1 /n " + this.getName() + " /n " + this.date;
        } else {
            return "E /n 0 /n " + this.getName() + " /n " + this.date;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
