public class Event extends Task {
    protected String dayAndDuration;
    public Event(String description, String dayAndDuration){
        super (description);
        this.dayAndDuration = dayAndDuration;
    }

    public String toStringTaskstxt(){
        return "D/" + getStatusIconInBin() + "/" + description + "/" + dayAndDuration + "\n";
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + dayAndDuration + ")";
    }
}
