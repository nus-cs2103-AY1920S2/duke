package duke.task;

/**
 * Represents a todo with a description.
 */
public class Todo extends Task {

    /**
     * Constructor for a todo task with user defined description.
     * Calls parent constructor with user input description.
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Takes in the arguments after the command keyword as input by the user.
     * Returns the todo task object, if successful, based on the input.
     * If incorrect arguments were specified, throws an IllegalArgumentException.
     * @param commandArgs the arguments after the command keyword as input by the user.
     * @return todo task object, if successful.
     * @throws IllegalArgumentException if user entered incorrect arguments.
     */
    public static Task createTask(String[] commandArgs) throws IllegalArgumentException {
        String description = "";
        for (String s : commandArgs) {
            if (s.equals("todo")) {
                continue;
            }
            description += s + " ";
        }

        if (description.equals("")) {
            throw new IllegalArgumentException("\u2639 OOPS!!! The description of a todo cannot be empty.");
        }

        return new Todo(description.trim());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}