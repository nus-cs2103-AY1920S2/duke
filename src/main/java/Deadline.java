public class Deadline extends Task{
    private String time;

    public Deadline(String instruction, String time) {
        super(instruction);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + time + ")";
    }
}
