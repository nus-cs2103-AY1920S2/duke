public class Deadline extends Item {

    private String keyword;
    private String deadline;

    /**
     * Contstructor of the deadline object.
     * @param name The task name.
     * @param keyword The keyword of the deadline.
     * @param deadline  The deadline date/time.
     */
    public Deadline(String name, String keyword, String deadline) {
        super(name);
        this.keyword = keyword;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (%s: %s)", super.toString(), keyword, deadline);
    }
}