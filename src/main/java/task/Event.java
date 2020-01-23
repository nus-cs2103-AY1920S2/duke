package task;

public class Event extends Task{
    private String time;

    public Event(String description, String time){
        super(description);
        this.time=time;
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString()+" (at: "+time+")";
    }
}
