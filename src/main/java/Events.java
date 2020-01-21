public class Events extends Task{
    protected String by;

    public Events(String description, String by) {
        super(description);
        this.by = by;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + by + ")";
    }
}
