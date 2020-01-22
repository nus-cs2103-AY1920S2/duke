public class Event extends Task {
    public String time;

    public Event(String name, String time) {
        super(name);
        done = false;
        this.time = time;
        count++;

    }
    public String toString() {
        if (done) {
            return ("[E][✓] " + name + " (at: "+time+ ")\n");
        }

        return ("[E][✗] " + name+ " (at: "+time+ ")\n");
    }
}
