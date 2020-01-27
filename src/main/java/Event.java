import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;

    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            String[] dateTimeArr = at.split(" ");
            this.date = LocalDate.parse(dateTimeArr[0]);
            String[] timeArr = dateTimeArr[1].split("-");
            this.startTime = LocalTime.parse(timeArr[0]);
            this.endTime = LocalTime.parse(timeArr[1]);
            if (startTime.isAfter(endTime)) {
                numOfTasks--;
                throw new DukeException("Start time cannot be after end time.");
            }
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            numOfTasks--;
            throw new DukeException("Incorrect date or time format. Format required: yyyy-mm-dd hh:mm-hh:mm");
        }
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + startTime + "-" + endTime + ")";
    }
}
