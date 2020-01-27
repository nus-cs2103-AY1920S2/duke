public class Deadline extends Task {
    protected String time;

    public Deadline(String desc, String time) {
        super(desc);
        this.time = time;
    }

    public Deadline(String desc, String time, boolean isDone) {
        super(desc);
        super.isDone = isDone;
        this.time = time;
    }

    @Override
    public String generateSaveFileEntry() {
        return String.format("D | %d | %s | %s\n", this.getStatusAsInt(), this.description, this.time);
    }

    @Override
    public String toString() {
        return String.format("[D] [%s] %s (by: %s)", this.getStatus(), this.description, this.time);
    }
}
