public class Task {
    private final String details;

    public Task(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return getDetails();
    }
}