package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate byDate = null;
    private LocalTime byTime = null;

    public Deadline(String description, String byDateTime) {
        super(description, false);

        //translate the date and time to the correct format
        String[] rawDateTime = byDateTime.split(" ");
        String[] date = rawDateTime[0].split("/");
        if (date[0].length() < 2) {
            date[0] = "0" + date[0];
        }
        if (date[1].length() < 2) {
            date[1] = "0" + date[1];
        }
        String formattedDate = date[2] + "-" + date[1] + "-"
                + date[0];
        String formattedTime = "" + rawDateTime[1].charAt(0)
                + rawDateTime[1].charAt(1) + ":" + rawDateTime[1].charAt(2)
                + rawDateTime[1].charAt(3) + ":00";

        //parse the reformatted date and time
        this.byDate = LocalDate.parse(formattedDate);
        this.byTime = LocalTime.parse(formattedTime);
    }
    
    public Deadline(String description, boolean isDone, String byDateTime) {
        super(description, isDone);

        //translate the date and time to the correct format
        String[] rawDateTime = byDateTime.split(" ");
        String[] date = rawDateTime[0].split("/");
        if (date[0].length() < 2) {
            date[0] = "0" + date[0];
        }
        if (date[1].length() < 2) {
            date[1] = "0" + date[1];
        }
        String formattedDate = date[2] + "-" + date[1] + "-"
                + date[0];
        String formattedTime = "" + rawDateTime[1].charAt(0)
                + rawDateTime[1].charAt(1) + ":" + rawDateTime[1].charAt(2)
                + rawDateTime[1].charAt(3) + ":00";

        //parse the reformatted date and time
        this.byDate = LocalDate.parse(formattedDate);
        this.byTime = LocalTime.parse(formattedTime);
    }
    
    @Override
    public String getSaveRepresentation() {
        return "D|||" + getIsDone() + "|||" + getDescription() + "|||"
                + byDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy "))
                + byTime.format(DateTimeFormatter.ofPattern("kmm")) + "\n";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s, %s)", "D", (getIsDone() ? "Y" : "N"), getDescription(),
                byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                byTime.format(DateTimeFormatter.ofPattern("h:mma")));
    }
}