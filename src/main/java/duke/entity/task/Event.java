package duke.entity.task;

import duke.parser.DateTimeParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    private String heldAt;
    private Date heldAtDate;
    private Date heldAtTime;
    private SimpleDateFormat TIMEFORMAT = new SimpleDateFormat("hh:mm aaa");
    private SimpleDateFormat DATEFORMAT = new SimpleDateFormat("EEE, d MMM yyyy");


    public Event(String taskName, String heldAt) {
        super(taskName);
        this.heldAt = heldAt;

        DateTimeParser dateTimeParser = new DateTimeParser();
        try {
            this.heldAtDate = dateTimeParser.parseDate(heldAt);
        } catch (ParseException e) {
            this.heldAtDate = null;
        }

        try {
            this.heldAtTime = dateTimeParser.parseTime(heldAt);
        } catch (ParseException e) {
            this.heldAtTime = null;
        }
        String addedInfo = (heldAtDate == null ? "" : DATEFORMAT.format(heldAtDate) + " ") + (heldAtTime == null ? "" : TIMEFORMAT.format(heldAtTime));
        super.setAddedInfo(addedInfo.equals("") ? heldAt : addedInfo);
    }

    public String heldAt() {
        return heldAt;
    }

    public void setHeldAt(String heldAt) {
        this.heldAt = heldAt;
    }

    public String getHeldAt() {
        return heldAt;
    }

    public Date getHeldAtDate() {
        return heldAtDate;
    }

    public void setHeldAtDate(Date heldAtDate) {
        this.heldAtDate = heldAtDate;
    }

    public Date getHeldAtTime() {
        return heldAtTime;
    }

    public void setHeldAtTime(Date heldAtTime) {
        this.heldAtTime = heldAtTime;
    }

    @Override
    public String printTask() {

        return "[E][" +  (super.isDone() ? "\u2713" : "\u2718" ) + "] "+ super.getTaskName() + " (at: " +
                (heldAtDate != null && heldAtTime != null ? DATEFORMAT.format(heldAtDate) + " " + TIMEFORMAT.format(heldAtTime) :
                        (heldAtDate != null ? DATEFORMAT.format(heldAtDate) : (heldAtTime != null ? TIMEFORMAT.format(heldAtTime) : heldAt))) + ")";
    }

    @Override
    public boolean isDue(Date date) {
        return this.heldAtDate != null ? this.heldAtDate.compareTo(date) != 1 ? true : false : false;
    }

    @Override
    public String toStringForm() {
        return "E|" +  (super.isDone() ? "1" : "0" ) + "|"+ super.getTaskName() + "|" + heldAt;
    }
}
