import exception.EmptyDescriptionException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public String input;
    public String command;
    public String[] inputs;

    public Parser(String input) {
        this.input = input;
    }

    /**
     * Returns the command such as "todo", "deadline", "event".
     * @return String command.
     */
    public String getCommand() {
        inputs = input.split(" ");
        command = inputs[0];
        return command;
    }

    /**
     * Gets the task depending on the command.
     * @return String representing task.
     */
    public String getTask() {
        switch (command) {
        case "todo":
            return inputs[1];
        case "find":
            return inputs[1];
        case "deadline":
            return input.split(" /by")[0].split(" ", 2)[1];
        case "event":
            return input.split(" /at")[0].split(" ", 2)[1];
        default:
            return "";
        }
    }

    /**
     * Gets the date depending on whether task is "deadline" or "event".
     * @return LocalDateTime object.
     */
    public LocalDateTime getDate() {
        if (inputs[0].equals("deadline")) {
            String time = input.split(" /by")[1].substring(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(time, formatter);
        } else if (inputs[0].equals("event")) {
            String time = input.split(" /at")[1].substring(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(time, formatter);
        } else {
            return null;
        }
    }

    /**
     * Gets the index for commands "done" and "delete".
     * @param tasks is the list of tasks.
     * @return an index integer for which task to manipulate.
     * @throws EmptyDescriptionException if command lacks index description.
     */
    public int getIndex(TaskList tasks) throws EmptyDescriptionException {
        if (input.split(" ").length == 1) {
            throw new EmptyDescriptionException("You forgot to mention the index!");
        }
        int idx = Integer.parseInt(input.split(" ")[1]) - 1;
        return idx;
    }
}
