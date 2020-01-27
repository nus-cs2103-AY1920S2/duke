public class Event extends Task {
    protected String by;
    private static final String eventTaskCode = "E";


    public Event(String desc, String by) {
        super(desc, eventTaskCode);
        this.by = by;
    }

    public Event(String desc, String by, String iDS) {
        this(desc, by);
        if(iDS.equals("O")) {
            this.done();
        }
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", this.taskCode, super.toString(), this.by);
    }

    @Override
    public String getSaveString() {
        return super.getSaveString() + "-" + this.by;
    }
}
