package Backend.Objects.Task;

import Backend.Constants.TaskChars;
import Backend.Parsers.DateParser;

public class Event extends Task {

    public Event(String content, DateParser date){
        super(content, date, TaskChars.EVENT_CHAR );
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + date.getDateString() + ")";
    }

}
