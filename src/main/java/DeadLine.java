public class DeadLine extends task {
    private String date;

    public DeadLine (String name, String end) {
        super(name);
        this.date = end;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
