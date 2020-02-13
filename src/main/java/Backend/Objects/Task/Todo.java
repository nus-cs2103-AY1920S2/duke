package Backend.Objects.Task;

public class Todo extends Task {

    public Todo(String content){
        super(content);
    }

    @Override
    public String toString() {
        return index + "." +" [T]" + super.toString();
    }
}
