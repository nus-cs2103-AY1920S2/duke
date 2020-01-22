public class ToDos extends Task {
    String by;

    public ToDos(String todo) {
        super(todo);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
