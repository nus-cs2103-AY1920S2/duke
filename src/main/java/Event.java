public class Event extends Task {
    public String time;

    public Event(int id, String name, String time) {
        super(id, name);
        done = false;
        this.time = time;
        count++;

    }
    public String toString() {
        if (done) {
            return (id + ".[E][✓] " + name + " (at: "+time+ ")");
        }

        return (id + ".[E][✗] " + name+ " (at: "+time+ ")");
    }
}
