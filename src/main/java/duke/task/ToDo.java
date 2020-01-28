package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toSaveName() {
        return "T" + super.toSaveName() +"\n";
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
