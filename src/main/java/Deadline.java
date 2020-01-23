public class Deadline extends Task{
    String time;

    public Deadline(String deadline, String time) {
        super(deadline);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + time + ")";
    }
}
