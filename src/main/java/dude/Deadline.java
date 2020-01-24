package dude;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    public static final String USAGE = "deadline description /by due_date";
    private String dueDate;
    private static Pattern messageRegex = Pattern.compile("deadline\\s+(\\S+.*)\\s+/by\\s+(\\S+.*)");

    public static Deadline parseDeadline(String msg) throws ParsingException {
        Matcher m = messageRegex.matcher(msg);
        
        if (m.matches()) {
            return new Deadline(m.group(1).strip(), m.group(2).strip(), false);
        } else {
            throw new ParsingException(USAGE);
        }
    }

    public Deadline(String details, String dueDate, boolean isDone) {
        super(details, isDone);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", dueDate);
    }

}