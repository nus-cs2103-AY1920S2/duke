import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task {

    // by is completion date.
    // d1 is the date that is being parsed
    // Time is the deadline completion time (IF THERE IS!)
    private String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by.trim();
    }


    // One way to get the time is we split the string, then we check if there
    // Is more character elements after the year.
    // If there is, then it means that there is time inclusive

    // Then for the date wise right, can just check if there is only one element
    // Or two elements.
    void setBy(String by) {

        this.by = super.set_by_at(by);
    }
    private String getBy() {
        return by;
    }

    // Get the date as a localdate event
    LocalDateTime getD1() {
        return d1;
    }

    void setD1() throws DukeException {
        super.setD1(getBy());
    }

    @Override
    String format_tasks(String s) throws DukeException {

        String[] splited_string = getDescription().split("deadline ");
        if(splited_string[0].length() <1) {
            throw new DukeException("You cannot leave the description empty");
        } else {
            try{
                return s.substring(s.indexOf("by")).replaceAll("by ", "");

            } catch (Exception e) {
                throw new DukeException("Please use ../by instead of any other identifiers ");
            }
        }

    }

    @Override
    void setDescription(String s) throws DukeException {
        try {
            String deadline_task = s.substring(s.indexOf("deadline"), s.indexOf("/"));
            String deadline_task2 = deadline_task.replaceAll("deadline", "").trim();
            super.setDescription(deadline_task2);
        } catch (Exception e) {
            throw new DukeException("Please enter the correct format for a deadline");
        }
    }

    @Override
    public String toString() {
        return " [" + Task_Codes.D + "]" + super.toString() + " (by: " +
                d1.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

}
