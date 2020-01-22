public class Deadline extends Task {
    String end;

    Deadline(String description, String end) {
        super(description);
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by:%s)", super.toString(), this.end);
    }
}
