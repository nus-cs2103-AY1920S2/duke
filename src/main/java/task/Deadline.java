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

public class Deadline extends Task {
    protected LocalDate byDay;
    protected LocalTime byTime;

    public Deadline(String description, String byDay, String byTime) {
        super(description);
        this.byDay = LocalDate.parse(byDay.trim(), DateTimeFormatter.BASIC_ISO_DATE);
        this.byTime = LocalTime.parse(byTime.trim(), DateTimeFormatter.ofPattern("HH:mm"));
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
                this.description, this.byDay.toString());
        return detail;
    }

    public void setUpTimer(HashMap<String, Timer> taskTimerMapping) throws Exception {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = dateFormatter.parse(String.format("%s %s", this.byDay.toString(), this.byTime.toString()));
        Timer timer = new Timer();
        timer.schedule(new ReminderTask(taskTimerMapping, this), date);
        taskTimerMapping.put(this.description, timer);
    }

}
