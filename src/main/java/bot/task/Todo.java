package bot.task;

import bot.command.Command;
import bot.command.exception.InadequateArgumentsException;

import bot.util.PrettyTime;

/**
 * Class that represents
 * "To-do" type Tasks
 */
public class Todo extends Task {
    public static String TYPE = "T";

    /**
     * Constructor for a To-do Task
     *
     * @param td String representing raw command with
     *           instruction
     * @throws InadequateArgumentsException If no description
     * is found for this To-do Task
     */
    public Todo(String td) throws InadequateArgumentsException {
        super(Todo.checkForNonEmpty(
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
        super(rt, new PrettyTime(""));
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
    public String getType() {
        return Todo.TYPE;
    }

    @Override
    public String getTimeVerb(String rawTime) {
        // no timeVerb as To-do Tasks are
        // not associated with a time
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
    private static String checkForNonEmpty(String todoCommand) throws InadequateArgumentsException {
        if (todoCommand.isBlank()) {
            throw new InadequateArgumentsException("todo");
        } else {
            return todoCommand;
        }
    }
}
