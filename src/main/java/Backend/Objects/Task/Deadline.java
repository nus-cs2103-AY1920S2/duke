package Backend.Objects.Task;

import Backend.Parsers.DateParser;

public class Deadline extends Task {

    public Deadline( String content, DateParser date) {
        super( content, date );
    }

    @Override
    public String toString() {
        return index + "." + " [D]" + super.toString() + " (by: " + date.getDateString() + ")";
    }
}
