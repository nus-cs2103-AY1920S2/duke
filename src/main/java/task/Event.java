package task;

import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = LocalDate.parse(eventTime.trim(), DateTimeFormatter.BASIC_ISO_DATE);
    }

    public String getShortName() {
        return "E";
    }

    /**
     * get full detail.
     * @param i index in array
     * @return
     */
    public String getFullDetail(Integer i) {
        String detail = String.format("%s[%s][%c] %s at %s\n", i, this.getShortName(),
                (char) (Integer.parseInt(this.getStatusIcon(), 16)),
                this.description, this.eventTime.toString());
        return detail;
    }

}
