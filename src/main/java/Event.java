import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Event extends Task{
    public Event(String description, LocalDate date) {
        super(description);
        this.type = "E";
        this.date = date;
    }

    public Event(String description, LocalDate date, ArrayList<Tag> tags) {
        super(description, tags);
        this.type = "E";
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder tags = new StringBuilder();
        if (!tagList.isEmpty()) {
            tagList.forEach(tag -> tags.append(tag.getDetails()));
        } else {
            tags.append("|");
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
