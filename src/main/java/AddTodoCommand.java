public class AddTodoCommand extends AddCommand {
    public AddTodoCommand(String description) {
        task = new Todo(description);
    }
}
