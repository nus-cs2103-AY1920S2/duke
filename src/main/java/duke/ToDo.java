package duke;

public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String writeDrive() {
        return "T|" + (super.isDone() ? "1|" : "0|") + this.name;
    }

    public ToDo setDone() {
        return new ToDo(this.name, true);
    }
}
