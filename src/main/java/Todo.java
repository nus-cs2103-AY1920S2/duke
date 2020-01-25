public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        String str = "[T]";
        if (this.isDone) {
            str += "[O] ";
        } else {
            str += "[X] ";
        }
        str += this.description;
        return str;
    }
}