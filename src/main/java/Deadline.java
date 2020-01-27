import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    protected String by = "";
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.date = LocalDate.parse(by);
        } catch(DateTimeParseException de) {
            this.by = by;
        }
    }

    public static Deadline create(String[] strArr) {
        Deadline t = new Deadline(strArr[2], strArr[3]);
        if(strArr[1].equals("1")) {t.setDone();}
        return t;
    }

    @Override
    public String store() {
        return "D|" + (isDone?"1":"0") + "|" + this.description + "|" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + (by.isEmpty()?date.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.LONG)):this.by) + ")";
    }
}
