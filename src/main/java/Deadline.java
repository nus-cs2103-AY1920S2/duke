import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
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

        // date_time is equal to everything after " /by"

        String[] splitted_string = by.split("/");
        String day_of_week_in_string = splitted_string[0];
        days = day_of_week_in_string.split(" ");
        if (dm.getHm().containsKey(days[0].toUpperCase())) {
            isDay = true;
        }

        // If the user enters "2" instead or "02", this changes it to "02"

        if (!isDay) {
            for (int j = 0; j < 2; j++) {
                if (splitted_string[j].length() != 2) {
                    splitted_string[j] = ("0" + splitted_string[j]);
                }
            }

            for (int i = 0; i < splitted_string.length - 1; i++) {
                this.by += splitted_string[i] + "/";
            }
            this.by += splitted_string[splitted_string.length - 1];
        }

        else {
            this.by = day_of_week_in_string;
        }
    }
    private String getBy() {
        return by;
    }

    // Get the date as a localdate event
    LocalDate getD1() {
        return d1;
    }

    void setD1() throws DukeException {
        super.setD1(getBy());
    }

    @Override
    String format_tasks(String s) throws DukeException {
        try {

            return s.substring(s.indexOf("by")).replaceAll("by ", "");
        } catch (Exception e) {
            throw new DukeException("Please use ../by for deadline");
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
                d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
