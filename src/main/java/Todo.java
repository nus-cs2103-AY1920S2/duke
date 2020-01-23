/**
 * Class that represents
 * "To-do" type Tasks
 */
public class Todo extends Task {
    public Todo(String td) throws InadequateArgumentsException {
        super(Todo.nonEmptyCheck(
            td.substring(Command.TODO.word.length()).stripLeading()),
         "");
    }

    @Override
    public String type() {
        return "T";
    }

    /**
     * Static method to check and throw an
     * Exception if todoCommand is empty
     * (only contains whitespace)
     *
     * @param todoCommand The String to be checked
     * @return The same String
     * @throws InadequateArgumentsException When the String
     *         is an empty String
     */
    private static String nonEmptyCheck(String todoCommand) throws InadequateArgumentsException {
        if (todoCommand.isBlank()) {
            throw new InadequateArgumentsException("todo");
        } else {
            return todoCommand;
        }
    }
}
