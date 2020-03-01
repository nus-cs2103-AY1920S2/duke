import java.time.LocalDate;

public class Event extends Task{
    public Event(String description, LocalDate date) {
        super(description);
        this.type = "E";
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder tags = new StringBuilder();
        if (!tagList.isEmpty()) {
            tags.append(" | ");
            tagList.forEach(tag -> tags.append(tag.getDetails()));
        }

        return String.format(
                "[%s][%s] %s (%s)%s",
                this.getType(),
                this.getStatusIcon(),
                this.getDescription(),
                this.formatDate(getDate()),
                tags);
    }
}
