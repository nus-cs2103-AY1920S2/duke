public class Events extends Task{
    protected String at;

    public Events(String description, Boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
        System.out.println(this);
    }

    public String getVenue() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}
