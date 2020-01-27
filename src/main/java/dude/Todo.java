package dude;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Todo extends Task {
    public static final String USAGE = "todo description";
    private static Pattern messageRegex = Pattern.compile("todo\\s+(\\S+.*)");

    public static Todo parseTodo(String msg) throws ParsingException {
        Matcher m = messageRegex.matcher(msg);
        
        if (m.matches()) {
            return new Todo(m.group(1).strip(), false);
        } else {
            throw new ParsingException(USAGE);
        }
    }

    public Todo(String details, boolean isDone) {
        super(details, isDone);
    }

    @Override
    public boolean occursOn(LocalDate date) {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String storeFormat() {
        return String.format("T|%s|%s", getStatusIcon(), getDetails());
    }
}