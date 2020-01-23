public class Deadline extends Task {
    protected String time;

    public Deadline(String desc, String time) {
        super(desc);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D] [%s] %s (by: %s)", this.getStatus(), this.description, this.time);
    }
}
