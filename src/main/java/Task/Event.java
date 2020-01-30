package Task;

public class Event extends Task {

    public Event(String desc, int index, DukeDate date){
        super(desc, index, date);
    }

    @Override
    public String toString() {
        return index + "." + "[E]" + super.toString() + " (at: " + date.getDateString() + ")";
    }
}
