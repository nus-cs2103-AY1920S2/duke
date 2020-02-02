import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private String eventDateString;
    private String eventTimeString;
    private LocalDate eventDate;
    private LocalTime eventTime;

    public Event(String type, String task) throws DukeException{
        super(type, task);

        try {
            String[] event = task.substring(task.indexOf("/")).split(" ");
            for(int i = 0; i < event.length; i++) {
                System.out.println(event[i]);
            }
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

        return super.toString();
    }
}

