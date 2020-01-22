public class Todo extends Task {

    public Todo(String description) {
        super(description);
        System.out.println("\t\t" + this);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}