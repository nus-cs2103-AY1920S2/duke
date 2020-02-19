package Backend.Objects.Task;

import Backend.Constants.TaskChars;
import Backend.Parsers.DateParser;

public class Deadline extends Task {

    public Deadline( String content, DateParser date) {
        super( content, date, TaskChars.DEADLINE_CHAR );
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + date.getDateString() + ")";
    }
}
