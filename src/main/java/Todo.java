public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String format() {
        return "T" + " | " + (this.isDone?"1":"0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}
