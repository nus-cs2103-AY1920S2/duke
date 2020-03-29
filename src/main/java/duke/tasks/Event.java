package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Event extends Task {
    protected String at = "";
    protected LocalDate date;

    /**
     * The constructor for Event object.
     * @param description The given description for this Event.
     * @param at The given description for the time this event starts in String.
     */
    public Event(String description, String at) {
        super(description);
        try {
            date = LocalDate.parse(at);
            assert this.date.isAfter(LocalDate.now()) : "Invalid early date";
            System.out.println("Parsed date successfully");
        } catch (DateTimeParseException de) {
            this.at = at;
        }
    }

    /**
     * Creates an Event class.
     * 
     * @param strArr Array of String containing input for the Event class.
     * @return Created Event class.
     */
    public static Event create(String[] strArr) {
        Event t = new Event(strArr[TASK_NAME_INDEX], strArr[3]);
        if (strArr[1].equals("1")) {
            t.setDone();
        }
        if (strArr.length > 4) {
            String tag = strArr[4];
            t.setTag(tag);
        }
        return t;
    }

    @Override
    public String store() {
        String dateType = this.at;
        if (dateType.isEmpty()) {
            dateType = this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        }
        return "E|" + (isDone ? "1" : "0") + "|" + this.description + "|" + dateType + "|" + this.tag;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "
                + (at.isEmpty() ? date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) : this.at) + ")"
                + " " + this.returnTag();
    }
}
