public class ToDo extends Task {

    protected String at;

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFileString() {
        return "T " + super.toFileString();
    }
}