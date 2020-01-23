/**
 * Class that represents
 * "To-do" type Tasks
 */
public class Todo extends Task {
    public Todo(String td) {
        super(td);
    }

    @Override
    public String type() {
        return "T";
    }
}
