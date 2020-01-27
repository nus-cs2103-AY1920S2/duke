package duke;

import java.lang.StringBuilder;

public class Todo extends Task {
    String description;

    Todo(String input) {
        super(input);
        this.description = getDescription(input);
    }

    protected String getDescription(String input) {
        StringBuilder str = new StringBuilder();
        String[] strArr = input.split(" ");
        for (int i = 1; i < strArr.length; i++ ) {
            str.append(strArr[i]).append(" ");
        }
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[T]");
        str.append(this.getStatusIcon()).append(" ").append(description);
        return str.toString();
    }
}
