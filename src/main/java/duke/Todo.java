package duke;

import java.lang.StringBuilder;

public class Todo extends Task {

    Todo(String input) {
        super(input);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[T]");
        str.append(this.getStatusIcon()).append(" ").append(input);
        return str.toString();
    }
}
