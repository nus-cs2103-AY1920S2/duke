package factory;

import command.Parser;
import tasks.Todo;

import java.time.LocalDate;

public class TodoFactory implements Factory<Todo> {
    private Parser parser;

    public TodoFactory() {
        parser = new Parser();
    }

    public Todo create(String input) {
        String[] parsed = input.split(" ", 2);
        if (parser.checkForTags(input)) {
            return new Todo(parsed[0], parsed[1]);
        } else {
            return new Todo(parsed[0]);
        }
    }
}

