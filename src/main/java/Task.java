public class Task {
    private String description;

    public Task (String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return String.format("%s", description);
    }
}
