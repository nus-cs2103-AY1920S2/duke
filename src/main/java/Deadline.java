import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

public class Deadline extends Task {

    private String deadline;
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;

    public Deadline(String description, String deadline) {

        super(description);
        int newDeadlineFormat = deadline.indexOf(" ");
        String actualDate = deadline.substring(newDeadlineFormat).trim(); // date without /by
        this.deadlineDate = parseDate(actualDate);
        this.deadlineTime = parseTime(actualDate);
        String formattedDate = this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedTime = this.deadlineTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        this.deadline = deadline.substring(0, newDeadlineFormat) + ": " + formattedDate + "  " + formattedTime;


    }

    private LocalTime parseTime(String actualDate) {

        int time = Integer.parseInt(actualDate.split(" ")[1]);
        LocalTime parsedTime = LocalTime.of(time / 100, time % 100);
        return parsedTime;

    }

    private LocalDate parseDate(String actualDate) {
        String date  = actualDate.split(" ")[0];
        LocalDate deadlineDate = LocalDate.parse(date);
        return deadlineDate;

    }

    @Override
    public String saveToHardDiskFormat() {

        return String.format("D | %d | %s | %s", this.completedCode, this.getDescription(), this.deadline.replace("by: ", ""));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + this.deadline + ")";
    }
}
