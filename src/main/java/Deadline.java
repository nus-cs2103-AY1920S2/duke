import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructor for the Deadline object, a subclass of Task
     * @param description String containing the description of the task
     * @param by String setting the deadline for this task
     */
    public Deadline(String description, String by) throws CathulhuException{
        super(description);
        DateTimeFormatter patternWithOptional = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd[ HH:mm]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .toFormatter();
        try {
            this.by = LocalDateTime.parse(by.strip(), patternWithOptional);
        } catch (Exception e) {
            throw new CathulhuException("\tInput date & time must follow the following format, mortal!"
                    + "\t  yyyy-MM-dd or yyyy-MM-dd HH:mm");
        }
    }



    /**
     * Formats this object as a String to be written into the data file
     * @return String in the format D:;:isDone:;:description:;:by
     */
    @Override
    public String toDataString() {
        return "D:;:" + super.toDataString() + ":;:" + this.by;
    }


    /**
     * Formats this object as a String to be printed out
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm ")) + ")";
    }
}