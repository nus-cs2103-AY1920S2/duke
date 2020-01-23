public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    private ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public ToDo setDone() {
        return new ToDo(this.name, true);
    }
}
