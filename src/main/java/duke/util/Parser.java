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
    private static String separator = "____________________________________________________________";
    private TaskList tasks;
    private Ui ui;

    Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    protected void parseInput(String line) {
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
            default:
                throw new InvalidCommandException();
            }
        } catch (IOException | DukeException e) {
            System.out.println(separator);
            System.err.println(e);
            System.out.println(separator);
        }
        parseInput(ui.getInput());

    }

    private String getCommand(String line) {
        return line.split(" ", 2)[0].trim();
    }

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
