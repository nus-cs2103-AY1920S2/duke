public class Deadline extends Item {
    String time;
    boolean done;
    Deadline(String name, String time) {
        super(name);
        this.time = time;
        this.done = false;
    }

    Deadline(String name, String time, boolean done) {
        super(name, done);
        this.time = time;
    }

    public String toString() {
        String temp = "   [D]" + super.toString() + " (by: "+ time + ")\n";
        return temp;
    }
    public String replace() {
        String temp = "   [D][✗] " + super.getName() + " (by: "+ time + ")\n";
        return temp;
    }
    @Override
    public String getType() {
        return "[D]";
    }
}
