public class Todo extends Task {

    public Todo(String description, int entrynum) {
        super(description, entrynum);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
