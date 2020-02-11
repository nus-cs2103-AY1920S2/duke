package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import duke.DukeException;

public class Deadline extends Task {

    protected LocalDateTime by;
    protected String byString;

    /**
     * Constructor for the Deadline object, a subclass of Task.
     *
     * @param description String containing the description of the task
     * @param by String setting the deadline for this task
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.byString = by;
        this.by = parseBy(this.byString);
    }

    /**
     * Parses input String object into a LocalDateTime object based on set patterns.
     *
     * @param by String to be parsed
     * @return LocalDateTime representation of time in the input String
     * @throws DukeException if the input String cannot be parsed
     */
    private LocalDateTime parseBy(String by) throws DukeException {
        DateTimeFormatter patternWithOptional = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd[ HH:mm]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .toFormatter();
        try {
            return LocalDateTime.parse(by.strip(), patternWithOptional);
        } catch (Exception e) {
            throw new DukeException("\tInput date & time must follow the following format, mortal!"
                    + "\t  yyyy-MM-dd or yyyy-MM-dd HH:mm");
        }
    }

    
    @Override
    public Task update(String[] updateStrArr) throws DukeException {
        super.update(updateStrArr);
        for (String updateStr : updateStrArr) {
            String[] attrToChange = updateStr.split("=");
            String attr = attrToChange[0].strip();
            String newValue = attrToChange[1].strip();
            if (attr.equalsIgnoreCase("by")) {
                this.byString = newValue;
                this.by = parseBy(this.byString);
            }
        }
        return this;
    }

    /**
     * Formats this object as a String to be written into the data file.
     *
     * @return String in the format D:;:isDone:;:description:;:by
     */
    @Override
    public String toDataString() {
        return "D:;:" + super.toDataString() + ":;:" + this.byString;
    }


    /**
     * Formats this object as a String to be printed out.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm ")) + ")";
    }
}