public class Todo extends Item {

    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean done) {
        super(name, done);
    }

    public String toSaveFormat() {
        return String.format("T | %s", super.toSaveFormat());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}