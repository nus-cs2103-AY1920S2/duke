package duke.util;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;

import java.io.IOException;

/**
 * The class that handles all commands entered.
 *
 * <p>CS2103T AY19/20 Semester 2
 * Individual Duke project
 * 11 Feb 2020
 *
 * @author Jel
 */
public class CommandHandler {
    private TaskList tasks;
    
    public CommandHandler(TaskList tasks) {
        this.tasks = tasks;
    }

    protected String handleCommand(String[] parsedInput, int taskNum, String keyword) {
        String cmd = parsedInput[0];
        String descAndDate = parsedInput[1];

        try {
            switch (cmd) {
            case "todo":
                return tasks.addTodo(descAndDate);
            case "deadline":
                return tasks.addDeadlineOrEvt(" /by ", descAndDate);
            case "event":
                return tasks.addDeadlineOrEvt(" /at ", descAndDate);
            case "list":
                return tasks.listTasks();
            case "done":
                return tasks.markTaskAsDone(taskNum);
            case "delete":
                return tasks.deleteTask(taskNum);
            case "find":
                return tasks.findTask(keyword);
            default:
                throw new InvalidCommandException();
            }
        } catch (IOException | DukeException e) {
            StringBuilder sb = new StringBuilder("____________________________________________________________\n");
            sb.append(e).append("\n").append("____________________________________________________________");
            return sb.toString();
        }
    }
}
