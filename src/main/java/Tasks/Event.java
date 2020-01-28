package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate by;
    private static final String eventTaskCode = "E";


    public Event(String desc, LocalDate by) {
        super(desc, eventTaskCode);
        this.by = by;
    }

    public Event(String desc, LocalDate by, String iDS) {
        this(desc, by);
        if(iDS.equals("O")) {
            this.done();
        }
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", this.taskCode, super.toString(), this.by.format(DateTimeFormatter.ofPattern("MMM d yyy")));
    }

    @Override
    public String getSaveString() {
        return super.getSaveString() + "|" + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
