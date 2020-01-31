package entity;

import parser.DateTimeParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    private String heldAt;
    private Date heldAtDate;
    private Date heldAtTime;

    public Event(String taskName, String heldAt) {
        super(taskName);
        this.heldAt = heldAt;

        DateTimeParser dateTimeParser = new DateTimeParser();
        System.out.println(heldAt);
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
        System.out.println(heldAtDate);
        System.out.println(heldAtTime);
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

    public String printTask() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aaa");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");

        return "[E][" +  (super.isDone() ? "\u2713" : "\u2718" ) + "] "+ super.getTaskName() + " (at: " +
                (heldAtDate != null && heldAtTime != null ? dateFormat.format(heldAtDate) + " " + timeFormat.format(heldAtTime) :
                        (heldAtDate != null ? dateFormat.format(heldAtDate) : (heldAtTime != null ? timeFormat.format(heldAtTime) : heldAt))) + ")";
    }

    public boolean isDue(Date date) {
        return this.heldAtDate != null ? this.heldAtDate.compareTo(date) != 1 ? true : false : false;
    }

    public String toStringForm() {
        return "E|" +  (super.isDone() ? "1" : "0" ) + "|"+ super.getTaskName() + "|" + heldAt;
    }
}
