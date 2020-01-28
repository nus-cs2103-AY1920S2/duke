import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Event extends Task {
    protected String at = "";
    protected LocalDate date;

    public Event(String description, String at) {
        super(description);
        try {
            date = LocalDate.parse(at);
            System.out.println("Parsed date successfully");
        } catch(DateTimeParseException de) {
            this.at = at;
        }
    }

    public static Event create(String[] strArr) {
        Event t = new Event(strArr[2], strArr[3]);
        if(strArr[1].equals("1")) {t.setDone();}
        return t;
    }

    @Override
    public String store() {
        String dateType = this.at;
        if (dateType.isEmpty()) {
            dateType = this.date.format(DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.LONG));
        }
        return "E|" + (isDone?"1":"0") + "|" + this.description + "|" + dateType;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "
                + (at.isEmpty()?date.format(DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.LONG)):this.at) + ")";
    }
}
