package task;

import exception.DukeException;
import parser.Parser;

public class Todo extends Task {
    public Todo(String description) throws DukeException {
        super(Constant.TODO.getType(), Parser.getContent(description));
    }

    public Todo(String[] fromMemory) {
        super(Constant.TODO.getType(), fromMemory[1], fromMemory[2]);
    }
}
