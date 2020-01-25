public class Deadline extends Task {

    protected TaskDate td;

    public Deadline(String desc, TaskDate td) {
        super(desc);
        this.td = td;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + td + ")";
    }
}