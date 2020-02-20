package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * The formatter used to parse user input.
     */
    public static final DateTimeFormatter PARSER = DateTimeFormatter
            .ofPattern("d-M-yyyy HHmm");
    /**
     * The formatter used for the file data format.
     */
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern("MMM d yyyy hhmma");

    /**
     * Creates a new Ui object to access functions.
     */
    private Ui ui = new Ui();
    /**
     * The Tasklist.
     */
    private TaskList taskList;

    /**
     * Creates a new Parser object and gets Tasklist from Duke.
     *
     * @param list the TaskList
     */
    public Parser(TaskList list) {
        this.taskList = list;
    }

    /**
     * Scans user input until a bye command is reached. parses each line
     * entered in by the user
     *
     * @param command user command
     * @return Duke's response to user command
     * @throws DukeException if the command cannot be parsed properly
     */
    public String parse(String command) throws DukeException {
        if (command.equals("list")) {
            return taskList.list();

        } else if (command.startsWith("done")) {
            ui.checkCommand(command, "done", taskList.size());
            return taskList.done(Integer.valueOf(command.split(" ")[1]) - 1);

        } else if (command.startsWith("delete")) {
            ui.checkCommand(command, "delete", taskList.size());
            return taskList.delete(Integer.valueOf(command.split(" ")[1]) - 1);
        } else {
            String[] arr = command.split("/");
            String[] description = (arr[0].split(" ", 2));

            if (command.startsWith("todo")) {
                ui.checkDescription(description, "todo");
                return taskList.add(new ToDo(description[1]), true);

            } else if (command.startsWith("deadline")) {
                ui.checkDescription(description, "deadline");
                ui.checkTime(arr, "deadline");
                try {
                    return taskList.add(new Deadline(description[1],
                            arr[1].split(" ", 2)[1], PARSER), true);
                } catch (DateTimeParseException e) {
                    return "Please enter the time in the <dd-MM-yyyy HHmm>"
                            + "format";
                }

            } else if (command.startsWith("event")) {
                ui.checkDescription(description, "event");
                ui.checkTime(arr, "event");
                try {
                    return taskList.add(new Event(description[1],
                            arr[1].split(" ", 2)[1], PARSER), true);
                } catch (DateTimeParseException e) {
                    return "Please enter the time in the <dd-MM-yyyy HHmm> "
                            + "format";
                }

            } else if (command.startsWith("find")) {
                return taskList.find(description[1]);

            } else if (command.startsWith("update")) {
                ui.checkUpdate(command, taskList.size());
                ui.checkTime(arr, "update");
                return taskList.update(Integer.valueOf(command.split(" ")[1]) - 1,
                        arr[1].split(" ", 2)[1], PARSER);

            } else {
                throw new DukeException("I'm sorry, but I don't know "
                        + "what that means :-(");
            }

        }
    }
}
