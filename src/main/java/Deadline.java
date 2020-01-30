public class Deadline extends Item {

    private String deadline;

    /**
     * Contsructor of the deadline object.
     * @param name The task name.
     * @param deadline  The deadline date/time.
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public Deadline(String name, String deadline, boolean done) {
        super(name, done);
        this.deadline = deadline;
    }

    public String toSaveFormat() {
        return String.format("D | %s | %s", super.toSaveFormat(), deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}