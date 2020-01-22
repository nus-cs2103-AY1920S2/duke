public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws DukeException{
        super(description);
        if (by.equalsIgnoreCase("")) {
            throw new DukeException("No date");
        }
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}