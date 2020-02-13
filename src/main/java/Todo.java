public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    @Override
    public String toString() {
        StringBuilder tags = new StringBuilder();
        if (!tagList.isEmpty()) {
            tags.append(" | ");
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
