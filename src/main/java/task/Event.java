package task;

import Server.ReminderTask;
import task.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;

public class Event extends Task {
    protected LocalDate eventDay;
    protected LocalTime eventTime;

    public Event(String description, String eventDay, String eventTime) {
        super(description);
        this.eventDay = LocalDate.parse(eventDay.trim(), DateTimeFormatter.BASIC_ISO_DATE);
        this.eventTime = LocalTime.parse(eventTime.trim(), DateTimeFormatter.ofPattern("HH:mm"));
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
                this.description, this.eventDay.toString());
        return detail;
    }

    public void setUpTimer(HashMap<String, Timer> taskTimerMapping) throws Exception {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = dateFormatter.parse(String.format("%s %s", this.eventDay.toString(), this.eventTime.toString()));
        Timer timer = new Timer();
        timer.schedule(new ReminderTask(taskTimerMapping, this), date);
        taskTimerMapping.put(this.description, timer);
    }
}
