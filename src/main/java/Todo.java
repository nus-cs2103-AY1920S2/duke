public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }


    @Override
    public String saveToHardDiskFormat() {

        return String.format("T | %d | %s", this.completedCode, this.getDescription());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
