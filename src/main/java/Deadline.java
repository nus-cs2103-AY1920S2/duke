public class Deadline extends Item {

    private String deadline;

    /**
     * Contstructor of the deadline object.
     * @param name The task name.
     * @param deadline  The deadline date/time.
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}