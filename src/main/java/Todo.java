import java.lang.reflect.Array;
import java.util.ArrayList;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    public Todo(String description, ArrayList<Tag> tags) {
        super(description, tags);
        this.type = "T";
    }

    @Override
    public String toString() {
        StringBuilder tags = new StringBuilder();
        if (!tagList.isEmpty()) {
            tagList.forEach(tag -> tags.append(tag.getDetails()));
        }

        return String.format(
                "[%s][%s] %s%s",
                this.getType(),
                this.getStatusIcon(),
                this.getDescription(),
                tags);
    }
}
