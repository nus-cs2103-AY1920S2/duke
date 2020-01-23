/**
 * Class that represents
 * "To-do" type Tasks
 */
public class Todo extends Task {
    public Todo(String td) {
        super(td.substring(Command.TODO.word.length()), "");
    }

    @Override
    public String type() {
        return "T";
    }
}
