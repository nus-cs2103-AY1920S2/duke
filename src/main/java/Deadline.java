import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task {

    // by is completion date.
    // d1 is the date that is being parsed
    // Time is the deadline completion time (IF THERE IS!)
    private String by;
    private LocalDate d1;

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
        String date_time = by.substring(by.indexOf("by")).replaceAll("by ", "");
        String[] splitted_string = date_time.split("/");

        // If the user enters "2" instead or "02", this changes it to "02"

        for(int j=0; j<2;j++) {
            if(splitted_string[j].length()!=2) {
                splitted_string[j] = ("0" + splitted_string[j]);
            }
        }

        for(int i=0; i< splitted_string.length-1; i++) {
            this.by += splitted_string[i] + "/";
        }
        this.by += splitted_string[splitted_string.length-1];
    }

    private String getBy() {
        return by;
    }

    // Get the date as a localdate event
    LocalDate getD1() {
        return d1;
    }

    void setD1() throws DukeException {

        // Means the string is entered as "2/12/2019",
        // We format it so that the date becomes "2019-12-02"
        if(getBy().contains("/")) {
            // DateTimeFormatter tells java what format is the input of
            // The date and time being entered.
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.d1 = LocalDate.parse(getBy(), dateFormat);
        } else if(getBy().contains("-")){

            // Parse the string into the LocalDate class
            // Provided that the string is formatted as
            // "2019-10-15", "yyyy-mm-dd"
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            this.d1 = LocalDate.parse(getBy(), dateFormat);
        } else  {
            throw new DukeException("Date is not in correct format!");
        }
    }

    @Override
    void setDescription(String s) throws DukeException {

        try {
            String deadline_task = s.substring(s.indexOf("deadline"), s.indexOf("/"));
            String deadline_task2 = deadline_task.replaceAll("deadline", "").trim();
            super.setDescription(deadline_task2);
        } catch (Exception e) {
            throw new DukeException("OOPS!! The description of a Deadline cannot be empty");
        }
    }

    @Override
    public String toString() {
        return " [" + Task_Codes.D + "]" + super.toString() + " (by: " +
                d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
