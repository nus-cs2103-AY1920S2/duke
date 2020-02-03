public class DeadLine extends task {
    private String date;

    public DeadLine (String name, String end) {
        super(name);
        this.date = end;
    }

    @Override
    public String toSave() {
        if (this.isDone()) {
            return "D /n 1 /n " + this.getName() + " /n " + this.date;
        } else {
            return "D /n 0 /n " + this.getName() + " /n " + this.date;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
