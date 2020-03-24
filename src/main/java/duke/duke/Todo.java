package duke.duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.typeD = "T";
    }

    @Override
    public String toString() {
        return String.format("[%s] [%s] %s",this.typeD,getStatusIcon(),this.description);
    }

}