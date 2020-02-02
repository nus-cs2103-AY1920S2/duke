public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    public String getType() { return "T"; }

    public String getDetails() { return ""; }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}