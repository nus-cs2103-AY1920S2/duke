public class EventObj extends Task {
    protected String datetime;
    public EventObj(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    @Override
    public String toString(){
        return super.toString() + "(at: " + datetime + ")";
    }
}
