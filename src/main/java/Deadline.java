public class Deadline extends Task {
    public String time;

    public Deadline(String name, String time) {
        super(name);
        done = false;
        this.time = time;
        count++;

    }
    public String toString() {
        if (done) {
            return ("[D][✓] " + name + " (by: "+time+ ")\n");
        }

        return ("[D][✗] " + name+ " (by: "+time+ ")\n");
    }
}
