public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        try {
            String[] time = (at.split(" ", 2));
            if (time.length < 2) {
                throw new DukeException("☹ OOPS!!! The time of a deadline cannot be empty.");
            }
            this.at = time[1];
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}