package duke;

import java.lang.StringBuilder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event that inherits from task, parses out the timing and description from input.
 */
public class Event extends Task {
    LocalDate timing;
    String description;

    Event(String input) throws DukeException {
        super(input);
        this.timing = getTiming(input);
        this.description = getDescription(input);
    }

    /**
     * Gets timing from the input parsed in the LocalDate format.
     * @param input from the string input user keys in.
     * @return timing as LocalData object.
     * @throws DukeException if date is the wrong format.
     */
    private LocalDate getTiming(String input) throws DukeException {
        try {
            String[] strArr = input.split(" ");
            int index = 0;
            for (int j = 0; j < strArr.length; j++) {
                String stringItem = strArr[j];
                if (stringItem.equals("/at")) {
                    index = j;
                    break;
                }
            }
            StringBuilder str = new StringBuilder();
            for (int i = index+1; i <strArr.length ; i++ ) {
                str.append(" ").append(strArr[i]);
            }
            String date = str.toString().trim();
            return LocalDate.parse(date);
        } catch (DateTimeParseException d) {
            throw new DukeException("date", "");
        }
    }

    /**
     * Gets description based on the input parsed.
     * @param input from the string input user keys in.
     * @return description as a String.
     */
    private String getDescription(String input) {
        StringBuilder str = new StringBuilder();
        String[] strArr = input.split(" ");
        for (int i = 1; i < strArr.length; i++ ) {
            if (strArr[i].equals("/at")) {
                break;
            }
            str.append(strArr[i]).append(" ");
        }
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[E]");
        str.append(this.getStatusIcon())
                .append(" ")
                .append(description)
                .append("(at: ")
                .append(timing.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                .append(")");
        return str.toString();
    }
}
