package factory;

import command.Parser;
import tasks.Todo;

import java.time.LocalDate;

/**
 * Generates todo objects based on user input.
 */
public class TodoFactory implements Factory<Todo> {
    private Parser parser;

    public TodoFactory() {
        parser = new Parser();
    }

    /**
     * Creates a todo object from user input.
     *
     * @param input input given by user.
     * @return todo object with the specified parameters.
     */
    public Todo create(String input) {
        String[] parsed = input.split(" ", 2);
        if (parser.checkForTags(input)) {
            return new Todo(parsed[0], parsed[1]);
        } else {
            return new Todo(parsed[0]);
        }
    }
}

