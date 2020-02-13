import java.time.LocalDate;

public class Event extends Task{
    public Event(String description, LocalDate date) {
        this.description = description;
        this.type = "E";
        this.date = date;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s][%s] %s (%s)",
                this.getType(),
                this.getStatusIcon(),
                this.getDescription(),
                this.formatDate(getDate()));
    }
}
