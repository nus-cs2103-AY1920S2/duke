package duke.exceptions;

import duke.ui.Ui;

public class UnrecognizedCommandException extends DukeException {
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Patrick, I don't recognize this command :(\n");
        output.append("Type 'help' if you need help!\n");
        return output.toString();
    }
}
