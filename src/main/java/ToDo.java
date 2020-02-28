public class ToDo extends Task {
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]"+ super.toString();
    }

    public String convert() {
        return "T" + super.convert();
    }
}
