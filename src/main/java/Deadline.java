public class Deadline extends Task {
    public Deadline(String description, String endTime) {
        super(description, endTime);
    }

    @Override
    public String getTypeName() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }

    @Override
    public String toStringFile() {
        return "D" + " | " + super.toStringFile();
    }
}
