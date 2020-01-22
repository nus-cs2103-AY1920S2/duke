public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        try {
            String[] time = (by.split(" ", 2));
            if (time.length < 2) {
                throw new DukeException("☹ OOPS!!! The time of a deadline cannot be empty.");
            }
            this.by = time[1];
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}