package dude;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public static final String USAGE = "deadline description /by yyyy-mm-dd";
    private LocalDate dueDate;
    private static Pattern messageRegex =
            Pattern.compile("deadline\\s+(\\S+.*)\\s+/by\\s+(\\d{4}-\\d{2}-\\d{2})");

    public static Deadline parseDeadline(String msg) throws ParsingException {
        Matcher m = messageRegex.matcher(msg);
        
        if (m.matches()) {
            return new Deadline(m.group(1).strip(), LocalDate.parse(m.group(2)), false);
        } else {
            throw new ParsingException(USAGE);
        }
    }

    public Deadline(String details, LocalDate dueDate, boolean isDone) {
        super(details, isDone);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                dueDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }

}