package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    protected String by = "";
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.date = LocalDate.parse(by);
            getParsed_date_successfully();
        } catch (DateTimeParseException de) {
            this.by = by;
        }
    }

    private void getParsed_date_successfully() {
        System.out.println("Parsed date successfully");
    }

    /**
     * Creates a Deadline class.
     * @param strArr Array of String containing input for the Deadline class.
     * @return Created Deadline class.
     */
    public static Deadline create(String[] strArr) {
        Deadline t = new Deadline(strArr[2], strArr[3]);
        if (strArr[1].equals("1")) {
            t.setDone();
        }
        return t;
    }

    @Override
    public String store() {
        String dateType = this.by;
        if (dateType.isEmpty()) {
            dateType = this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        }
        return "D|" + (isDone ? "1" : "0") + "|" + this.description + "|" + dateType;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + (by.isEmpty() ? date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) : this.by) + ")";
    }
}
