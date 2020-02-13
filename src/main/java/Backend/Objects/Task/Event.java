package Backend.Objects.Task;

import Backend.Parsers.DateParser;

public class Event extends Task {

    private DateParser date;

    public Event(String content, DateParser date){
        super(content, date);
    }

    @Override
    public String toString() {
        return index + "." + " [E]" + super.toString() + " (at: " + date.getDateString() + ")";
    }

}
