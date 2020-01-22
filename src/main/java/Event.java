public class Event extends Task {
    String dayAndDuration;
    public Event(String description, String dayAndDuration){
        super (description);
        this.dayAndDuration = dayAndDuration;
    }

    @Override
    public String toString(){
        return "[E]" + getStatusIcon() + description + "(at: " + dayAndDuration + ")";
    }
}
