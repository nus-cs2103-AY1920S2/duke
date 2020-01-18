package duke.commands;

import java.util.List;
import duke.tasks.Task;
import duke.exceptions.DukeException;

public interface Command {
    void execute(String arg, List<Task> tasks) throws DukeException;

    default String formatReply(String str) {
        String[] lines = str.split("\\r?\\n");
        StringBuilder sb = new StringBuilder();
        String lineBreak = "===========================================================\n";
        for (String line : lines) {
            sb.append("> ");
            sb.append(line);
            sb.append("\n");
        }
        sb.append(lineBreak);
        return sb.toString();
    }
}

