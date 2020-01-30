package duke.util;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.NoDateProvidedException;
import duke.exception.NoDescriptionException;

import java.io.IOException;

/**
 * Parser
 *
 * CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 30 Jan 2020
 *
 * @author Jel
 */
public class Parser {
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Parser instance.
     * @param tasks List where tasks are to be stored.
     * @param ui User interface of the program.
     */
    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Parses user input.
     * @param line The line of input to be parsed.
     */
    public void parseInput(String line) {
        String trimmed = line.trim();
        String cmd = getCommand(trimmed);
        String descAndDate;

        try {
            switch(cmd) {
            case "bye":
                ui.bye();
                return;
            case "todo":
                descAndDate = getDescAndDate(cmd, trimmed);
                tasks.addTodo(descAndDate);
                break;
            case "deadline":
                descAndDate = getDescAndDate(cmd, trimmed);
                tasks.addDeadlineOrEvt(" /by ", descAndDate);
                break;
            case "event":
                descAndDate = getDescAndDate(cmd, trimmed);
                tasks.addDeadlineOrEvt(" /at ", descAndDate);
                break;
            case "list":
                tasks.listTasks();
                break;
            case "done":
                tasks.markTaskAsDone(trimmed);
                break;
            case "delete":
                tasks.deleteTask(trimmed);
                break;
            case "find":
                tasks.findTask(trimmed);
                break;
            default:
                throw new InvalidCommandException();
            }
        } catch (IOException | DukeException e) {
            System.out.println("____________________________________________________________");
            System.err.println(e);
            System.out.println("____________________________________________________________");
        }
        parseInput(ui.getInput());

    }

    /**
     * Extracts user's command from line of input.
     * @param line The user's line of input.
     * @return The user's command.
     */
    private String getCommand(String line) {
        return line.split(" ", 2)[0].trim();
    }

    /**
     * Extracts the description and date of the task.
     * @param cmd The user's command.
     * @param line The user's line of input.
     * @return A joined string of the description and date of the task.
     * @throws NoDescriptionException No description for the task was provided.
     * @throws NoDateProvidedException No description for the deadline or event was provided.
     */
    private String getDescAndDate(String cmd, String line) throws NoDescriptionException, NoDateProvidedException {
        String[] arr = line.split(" ", 2);
        if (arr.length < 2) {
            throw new NoDescriptionException();
        }
        if (cmd.equals("todo")) {
            return arr[1].trim();
        } else if (cmd.equals("event")) {
            String[] descAndDate = arr[1].split(" /at ");
            if (descAndDate.length < 2) {
                throw new NoDateProvidedException("at");
            }
            return String.join(" | ", descAndDate);
        } else {
            String[] descAndDate = arr[1].split(" /by ");
            if (descAndDate.length < 2) {
                throw new NoDateProvidedException("by");
            }
            return String.join(" | ", descAndDate);
        }
    }
}
