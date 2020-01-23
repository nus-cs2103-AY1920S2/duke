public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getFullDescription() {
        return "[T]" + super.getDescriptionWithIsDone();
    }
}