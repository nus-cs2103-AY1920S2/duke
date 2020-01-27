public class Deadline extends Task {
    protected String by;
    private static final String deadlineTaskCode = "D";

    public Deadline(String desc, String by) {
        super(desc, deadlineTaskCode);
        this.by = by;
    }

    public Deadline(String desc, String by, String iDS) {
        this(desc, by);
        if(iDS.equals("O")) {
            this.done();
        }
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", this.taskCode, super.toString(), this.by);
    }

    @Override
    public String getSaveString() {
        return super.getSaveString() + "-" + this.by;
    }

}
