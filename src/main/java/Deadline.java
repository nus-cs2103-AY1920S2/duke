public class Deadline extends Task {
    public String time;

    public Deadline(int id, String name, String time) {
        super(id, name);
        done = false;
        this.time = time;
        count++;

    }
    public String toString() {
        if (done) {
            return (id + ".[D][✓] " + name + " (by: "+time+ ")");
        }

        return (id + ".[D][✗] " + name+ " (by: "+time+ ")");
    }
}
