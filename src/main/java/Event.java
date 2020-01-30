public class Event extends Task {
    String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toFile() {
        int doneInt = done ? 1 : 0;
        return "E , " + doneInt + " , " + name + " , " + time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time + ")";
    }
}
