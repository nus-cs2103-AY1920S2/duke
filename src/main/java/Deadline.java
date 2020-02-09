import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate byTime;

    public Deadline(String description, String byTime) {
        super(description);
        this.byTime = LocalDate.parse(byTime.trim(), DateTimeFormatter.BASIC_ISO_DATE);
    }

    public String getShortName() {
        return "D";
    }

    /**
     * get full detail.
     * @param i index in array
     * @return
     */
    public String getFullDetail(Integer i) {
        String detail = String.format("%s[%s][%c] %s by %s\n", i, this.getShortName(),
                (char) (Integer.parseInt(this.getStatusIcon(), 16)),
                this.description, this.byTime.toString());
        return detail;
    }

}
