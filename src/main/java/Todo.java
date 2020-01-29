/**
 * Class that represents
 * "To-do" type Tasks
 */
public class Todo extends Task {
    public static String TYPE = "T";

    /**
     * Constructor for a To-do task
     *
     * @param td String representing raw command with
     *           instruction
     * @throws InadequateArgumentsException
     */
    public Todo(String td) throws InadequateArgumentsException {
        super(Todo.nonEmptyCheck(
                td.substring(Command.TODO.word.length()).stripLeading()),
                new PrettyTime(""));
    }

    /**
     * Constructor for a To-do task
     *
     * @param rt The raw description of the To-do
     * @param isRaw Redundant boolean to ensure different
     *              type signature from public constructor
     */
    private Todo(String rt, boolean isRaw) {
        super(rt, "");
    }

    /**
     * Initialiser for a To-do Task
     *
     * @param rawText The raw description of the To-do task
     */
    public static Todo makeTodoRaw(String rawText) {
        return new Todo(rawText, true);
    }

    @Override
    public String type() {
        return Todo.TYPE;
    }

    @Override
    public String timeVerb(String rawTime) {
        return "";
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
