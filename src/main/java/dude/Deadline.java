package dude;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    public static final String USAGE = "deadline description /by due_date";
    private String dueDate;
    private static Pattern messageRegex = Pattern.compile("deadline\\s+(\\S+.*)\\s+/by\\s+(\\S+.*)");

    public static Deadline parseDeadline(String msg) throws MessageInterpretationException {
        Matcher m = messageRegex.matcher(msg);
        
        if (m.matches()) {
            return new Deadline(m.group(1), m.group(2));
        } else {
            throw new MessageInterpretationException(USAGE);
        }
    }

    public Deadline(String details, String dueDate) {
        super(details);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", dueDate);
    }
}