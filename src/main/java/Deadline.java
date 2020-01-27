import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String word;
    protected String date;
    protected String time;
    protected String dateLine;
    protected String originalDate;

    public Deadline(String s) {
        super(s);
        description = s.split("/")[0];
        date = s.split("/")[1];
        word = date.substring(0, date.indexOf(" "));
        dateLine = date.substring(date.indexOf(" ") + 1, date.length());
        date = dateLine.split(" ")[0];
        time = dateLine.split(" ")[1];
        originalDate = date;
        date = changeDate();
        time = changeTime();
    }

    public String getType() {
        return "D";
    }

    public String getTask() {
        return description + "(" +word+ ": " +date+ " " +time +")";
    }

    public String getDate() {
        return originalDate;
    }

    public String getTime() {
        return time;
    }

    public String changeDate() {
        DateTime dt = new DateTime();
        return dt.convertDate(date);
    }

    public String changeTime() {
        DateTime t = new DateTime();

        return t.convertTime(time);
    }

    public String getWord() {
        return word;
    }

    public String getDescription() {
        return description;
    }
}
