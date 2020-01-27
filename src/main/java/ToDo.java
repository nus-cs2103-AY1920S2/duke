public class ToDo extends Task {

    protected String by;

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String format() {
        return "T " + super.getStatusInNumber() + " " + super.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}