import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task {
    protected String deadlineDate;
    protected String deadlineTime;
    protected String deadline;

    public Deadline(String command, String deadlineDate, String deadlineTime) {
        super(command);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
            Date deadlineTimeParsed = sdf.parse(deadlineTime);
            SimpleDateFormat sdf2 = new SimpleDateFormat("hhmm aa");
            this.deadline = LocalDate.parse(deadlineDate).format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                    + sdf2.format(deadlineTimeParsed);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String assembleDeadlineDateAndTime() {
        return deadlineDate + " " + deadlineTime;
    }

    public int getDoneInt() {
        return getDone() ? 1 : 0;
    }

    @Override
    public String updateFile() {
        return "D - " + getDoneInt() + " - " + getCommand() + " - " + assembleDeadlineDateAndTime();
    }

    @Override
    public String toString() {
        return "[D][" + getDoneSymbol() + "] " + getCommand() + " (by: " + deadline + ")";
    }
}
