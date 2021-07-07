package duke.entity.task;

import duke.parser.DateTimeParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    private String doBy;
    private Date doByDate;
    private Date doByTime;
    private final SimpleDateFormat TIMEFORMAT = new SimpleDateFormat("hh:mm aaa");
    private final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("EEE, d MMM yyyy");

    public Deadline(String taskName, String doBy) {

        super(taskName);
        this.doBy = doBy;
        DateTimeParser dateTimeParser = new DateTimeParser();

        try {
            this.doByDate = dateTimeParser.parseDate(doBy);
        } catch (ParseException e) {
            this.doByDate = null;
        }

        try {
            this.doByTime = dateTimeParser.parseTime(doBy);
        } catch (ParseException e) {
            this.doByTime = null;
        }
        String addedInfo = (doByDate == null ? "" : DATEFORMAT.format(doByDate) + " ") + (doByTime == null ? "" : TIMEFORMAT.format(doByTime));
        super.setAddedInfo(addedInfo.equals("") ? doBy : addedInfo);
    }

    public String getDoBy() {
        return doBy;
    }

    public void setDoBy(String doBy) {
        this.doBy = doBy;
    }

    public Date getDoByDate() {
        return doByDate;
    }

    public void setDoByDate(Date doByDate) {
        this.doByDate = doByDate;
    }

    public Date getDoByTime() {
        return doByTime;
    }

    public void setDoByTime(Date doByTime) {
        this.doByTime = doByTime;
    }

    @Override
    public String printTask() {
        return "[D][" + (super.isDone() ? "\u2713" : "\u2718" ) + "] "+ super.getTaskName() + " (by: " +
                (doByDate != null && doByTime != null ? DATEFORMAT.format(doByDate) + " " + TIMEFORMAT.format(doByTime) :
                        (doByDate != null ? DATEFORMAT.format(doByDate) : (doByTime != null ? TIMEFORMAT.format(doByTime) : doBy))) + ")";
    }

    @Override
    public boolean isDue(Date date) {
        return this.doByDate != null ? this.doByDate.compareTo(date) != 1 ? true : false : false;
    }

    @Override
    public String toStringForm() {
        return "D|" +  (super.isDone() ? "1" : "0" ) + "|"+ super.getTaskName() + "|" + doBy;
    }
}
