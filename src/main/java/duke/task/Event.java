package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

public class Event extends Task {

    private String eventDateString;
    private String eventTimeString;
    private LocalDate eventDate;
    private LocalTime eventTime;

    public Event(String type, String task) throws DukeException {
        super(type, task);

        try {
            String[] event = task.substring(task.indexOf("/")).split(" ");
            if(event.length != 3) {
                throw new DukeException("dateTime");
            }

            this.eventDateString = event[1];
            this.eventTimeString = event[2];
            this.eventDate = LocalDate.parse(this.eventDateString, dateFormatter);
            this.eventTime = LocalTime.parse(this.eventTimeString, timeFormatter);

        } catch(Exception e) {
            throw new DukeException("dateTime");
        }
    }



    @Override
    public String toString() {

        String checkmark = "N";

        if (this.isDone == true) {
            checkmark = "Y";
        }

        String output = "[" + this.type + "]" + "[" + checkmark + "] ";

        String task_Name2 = task.substring(task.indexOf(" "), task.indexOf("/") - 1);
        output += task_Name2 + " (by: " + eventDate.format(
                DateTimeFormatter.ofPattern("dd MMM yyyy")) + " " + eventTime.format(
                DateTimeFormatter.ofPattern("h:mm a")) + ")";
        return output;
    }
}

