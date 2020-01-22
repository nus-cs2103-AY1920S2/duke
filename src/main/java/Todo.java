public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        String str = "[T]";
        if (this.isDone) {
            str += "[\u2713] ";
        } else {
            str += "[\u2718] ";
        }
        str += this.description;
        return str;
    }
}