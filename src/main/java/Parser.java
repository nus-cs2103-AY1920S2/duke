import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

import static java.lang.Integer.parseInt;

public class Parser {
    /**
     * Parse command related to task.
     * @param cmd
     * @return An add task command
     */
    private Command parseTask(String cmd) {
        if (cmd.startsWith("todo")) {
            cmd = cmd.substring("todo".length()).strip();
            return new CommandAddToDo(cmd);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-M-d H:mm");
        if (cmd.startsWith("deadline")) {
            cmd = cmd.substring("deadline".length()).strip();
            String[] token = cmd.split("/");

            if (token.length != 2) {
                throw new InputMismatchException("Wrong format. Expected deadline <name>/<yy-M-d H:mm>.");
            }
            return new CommandAddDeadline(token[0], LocalDateTime.parse(token[1].strip(), formatter));
        }
        if (cmd.startsWith("event")) {
            cmd = cmd.substring("event".length()).strip();
            String[] token = cmd.split("/");
            if (token.length != 2) {
                throw new InputMismatchException("Wrong format. Expected event <name>/<yy-M-d H:mm>.");
            }
            return new CommandAddEvent(token[0], LocalDateTime.parse(token[1].strip(), formatter));
        }
        throw new InputMismatchException("Unknown command");
    }

    /**
     * Parse a given string to a command. Will throw exception if the command is unknown
     * @param input
     * @return A command
     * @throws Exception
     */

    public Command parse(String input) throws Exception {
        if (input.equals("list")) {
            return new CommandList();
        }
        if (input.equals("sort")) {
            return new CommandSort();
        }
        if (input.startsWith("find")) {
            input = input.substring("find".length()).strip();
            return new CommandFind(input);
        }
        if (input.startsWith("done")) {
            String[] token = input.split(" ");

            if (token.length != 2) {
                throw new Exception("Wrong format. Expected done <task-number>.");
            }
            int pos = parseInt(token[1]);
            return new CommandDone(pos);
        }
        if (input.startsWith("erase")) {
            String[] token = input.split(" ");
            if (token.length != 2) {
                throw new Exception("Wrong format. Expected erase <task-number>.");
            }
            int pos = parseInt(token[1]);
            return new CommandErase(pos);
        }
        return parseTask(input);
    }
}
